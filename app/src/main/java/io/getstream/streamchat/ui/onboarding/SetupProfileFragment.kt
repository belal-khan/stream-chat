package io.getstream.streamchat.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import io.getstream.streamchat.ui.chat.ChatActivity
import io.getstream.streamchat.R
import io.getstream.streamchat.data.UserExtra
import io.getstream.streamchat.databinding.FragmentSetupProfileBinding
import io.getstream.streamchat.ui.base.BaseFragment
import io.getstream.streamchat.ui.setViewEnabled
import io.getstream.streamchat.ui.snackbar
import io.getstream.streamchat.ui.startNewActivity
import javax.inject.Inject

@AndroidEntryPoint
class SetupProfileFragment : BaseFragment(R.layout.fragment_setup_profile) {

  private lateinit var binding: FragmentSetupProfileBinding
  private val viewModel by viewModels<OnboardingViewModel>()

  @Inject
  lateinit var streamTokenProvider: StreamTokenProvider

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
      id = currentUser.uid,
      extraData = mutableMapOf(
        UserExtra.NAME to binding.editTextName.text.toString(),
        UserExtra.PHONE to currentUser.phoneNumber.toString(),
        UserExtra.IMAGE to UserExtra.DEFAULT_AVATAR,
      )
    )
    ChatClient.instance().connectUser(user, streamTokenProvider.getTokenProvider(currentUser.uid))
      .enqueue {
        if (it.isSuccess) {
          requireActivity().startNewActivity(ChatActivity::class.java)
        } else {
          snackbar("${it.error().message}")
        }
      }
  }
}

