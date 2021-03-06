package io.getstream.streamchat.ui.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.streamchat.data.network.Resource
import io.getstream.streamchat.data.repository.StreamChatRepository
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
  private val repo: StreamChatRepository
) : ViewModel() {

}
