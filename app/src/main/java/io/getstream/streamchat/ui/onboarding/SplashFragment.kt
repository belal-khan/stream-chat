package io.getstream.streamchat.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import io.getstream.streamchat.ui.chat.ChatActivity
import io.getstream.streamchat.R
import io.getstream.streamchat.data.UserExtra
import io.getstream.streamchat.databinding.FragmentSplashBinding
import io.getstream.streamchat.ui.base.BaseFragment
import io.getstream.streamchat.ui.startNewActivity
import javax.inject.Inject


@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

  private lateinit var binding: FragmentSplashBinding

  @Inject
  lateinit var streamTokenProvider: StreamTokenProvider

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentSplashBinding.bind(view)
    binding.buttonContinue.setOnClickListener {
      findNavController().navigate(R.id.enterPhoneFragment)
    }
  }

  override fun checkIfUserAlreadyInitialized() {
    val user = User(id = currentUser.uid)
    ChatClient.instance().connectUser(user, streamTokenProvider.getTokenProvider(currentUser.uid))
      .enqueue {
        if (it.isSuccess) {
          val usr = it.data().user
          val name = usr.extraData[UserExtra.NAME] as String?
          if (name.isNullOrEmpty()) {
            findNavController().navigate(R.id.setupProfileFragment)
          } else
            requireActivity().startNewActivity(ChatActivity::class.java)
        }
      }
  }
}
