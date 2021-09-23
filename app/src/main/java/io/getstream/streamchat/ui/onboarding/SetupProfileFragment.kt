package io.getstream.streamchat.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.token.TokenProvider
import io.getstream.streamchat.R
import io.getstream.streamchat.data.UserExtra
import io.getstream.streamchat.databinding.FragmentSetupProfileBinding
import io.getstream.streamchat.ui.base.BaseFragment
import io.getstream.streamchat.ui.setViewEnabled

class SetupProfileFragment : BaseFragment(R.layout.fragment_setup_profile) {

  private lateinit var binding: FragmentSetupProfileBinding

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
  }

  val tokenProvider = object : TokenProvider {
    override fun loadToken(): String = currentUser.uid
  }
}
