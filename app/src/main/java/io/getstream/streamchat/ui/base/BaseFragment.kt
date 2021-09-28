package io.getstream.streamchat.ui.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User
import io.getstream.streamchat.R
import io.getstream.streamchat.ui.onboarding.OnBoardingActivity
import io.getstream.streamchat.ui.startNewActivity

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

  protected lateinit var currentUser: FirebaseUser

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    with(FirebaseAuth.getInstance().currentUser) {
      if (this != null) {
        currentUser = this
        checkIfUserAlreadyInitialized()
      } else {
        navigateToEnterPhoneFragment()
      }
    }
  }

  private fun navigateToEnterPhoneFragment() {
    when (requireActivity()) {
      is OnBoardingActivity -> {
        if (findNavController().currentDestination?.id == R.id.setupProfileFragment) {
          findNavController().navigate(R.id.enterPhoneFragment)
        }
      }
      else -> requireActivity().startNewActivity(OnBoardingActivity::class.java)
    }
  }

  open fun checkIfUserAlreadyInitialized(){}
}
