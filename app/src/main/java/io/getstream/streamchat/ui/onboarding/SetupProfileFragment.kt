package io.getstream.streamchat.ui.onboarding

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.token.TokenProvider
import io.getstream.streamchat.R
import io.getstream.streamchat.data.UserExtra
import io.getstream.streamchat.databinding.FragmentSetupProfileBinding
import io.getstream.streamchat.ui.base.BaseFragment
import io.getstream.streamchat.ui.setViewEnabled
import io.getstream.streamchat.ui.snackbar
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class SetupProfileFragment : BaseFragment(R.layout.fragment_setup_profile) {

  private lateinit var binding: FragmentSetupProfileBinding
  private val viewModel by viewModels<OnboardingViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentSetupProfileBinding.bind(view)

    binding.buttonNext.setViewEnabled(false)

    binding.editTextName.addTextChangedListener {
      binding.buttonNext.setViewEnabled(it?.isNotEmpty() == true)
    }

    binding.buttonNext.setOnClickListener {
      setupUser()
    }
  }

  private fun setupUser() {
    val user = User(
      id = currentUser.phoneNumber!!,
      extraData = mutableMapOf(
        UserExtra.NAME to binding.editTextName.text.toString(),
        UserExtra.IMAGE to UserExtra.DEFAULT_AVATAR,
      )
    )
    ChatClient.instance().connectUser(user, TokenProviderImpl(currentUser.phoneNumber!!)).enqueue {
      if (it.isSuccess) {
        snackbar("User Connected")
      } else {
        snackbar("${it.error().message}")
        Log.e("Error1", it.error().message.toString())
        Log.e("Error2", it.error().cause?.message.toString())
      }
    }
  }

  inner class TokenProviderImpl(
    private val userId: String
  ) : TokenProvider {
    override fun loadToken(): String = runBlocking {
      viewModel.getUserToken(userId) ?: ""
    }
  }
}

