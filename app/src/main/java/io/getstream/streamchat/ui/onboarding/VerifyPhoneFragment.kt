package io.getstream.streamchat.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import io.getstream.streamchat.R
import io.getstream.streamchat.databinding.FragmentVerifyPhoneBinding
import io.getstream.streamchat.ui.base.BaseFragment
import io.getstream.streamchat.ui.setViewEnabled
import io.getstream.streamchat.ui.snackbar
import java.util.concurrent.TimeUnit

class VerifyPhoneFragment : BaseFragment(R.layout.fragment_verify_phone) {

  private lateinit var binding: FragmentVerifyPhoneBinding
  private lateinit var auth: FirebaseAuth
  private lateinit var storedVerificationId: String
  private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentVerifyPhoneBinding.bind(view)
    binding.buttonNext.setViewEnabled(false)

    auth = FirebaseAuth.getInstance()

    val number = arguments?.getString(KEY_PHONE) ?: return

    val options = PhoneAuthOptions.newBuilder(auth)
      .setPhoneNumber(number)
      .setTimeout(60L, TimeUnit.SECONDS)
      .setActivity(requireActivity())
      .setCallbacks(callbacks)
      .build()

    PhoneAuthProvider.verifyPhoneNumber(options)

    binding.editTextOtp.addTextChangedListener {
      binding.buttonNext.setViewEnabled(it?.toString()?.length == 6)
    }

    binding.buttonNext.setOnClickListener {
      val code = binding.editTextOtp.text.toString().trim()
      val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
      signInWithPhoneAuthCredential(credential)
    }
  }

  private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
      signInWithPhoneAuthCredential(credential)
    }

    override fun onVerificationFailed(e: FirebaseException) {
      if (e is FirebaseAuthInvalidCredentialsException) {
        snackbar(getString(R.string.invalid_code_entered))
      } else if (e is FirebaseTooManyRequestsException) {
        snackbar(getString(R.string.try_later))
      }
    }

    override fun onCodeSent(
      verificationId: String,
      token: PhoneAuthProvider.ForceResendingToken
    ) {
      storedVerificationId = verificationId
      resendToken = token
    }
  }

  private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
    auth.signInWithCredential(credential)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {
          findNavController().navigate(R.id.setupProfileFragment)
        } else {
          if (task.exception is FirebaseAuthInvalidCredentialsException) {
            snackbar(getString(R.string.invalid_code_entered))
          }
        }
      }
  }

  companion object {
    const val KEY_PHONE = "key_phone"
  }
}
