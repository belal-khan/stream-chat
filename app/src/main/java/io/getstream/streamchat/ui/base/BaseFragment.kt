package io.getstream.streamchat.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.getstream.streamchat.R

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

  protected lateinit var currentUser: FirebaseUser

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    with(FirebaseAuth.getInstance().currentUser) {
      if (this != null) {
        currentUser = this
      } else {
        navigateToEnterPhoneFragment()
      }
    }
  }

  private fun navigateToEnterPhoneFragment() {
    if (findNavController().currentDestination?.id == R.id.setupProfileFragment) {
      findNavController().navigate(R.id.enterPhoneFragment)
    }
  }
}
