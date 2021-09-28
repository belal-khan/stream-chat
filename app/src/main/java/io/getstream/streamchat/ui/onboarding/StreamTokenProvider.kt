package io.getstream.streamchat.ui.onboarding

import io.getstream.chat.android.client.token.TokenProvider
import io.getstream.streamchat.data.repository.StreamChatRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class StreamTokenProvider @Inject constructor(
  private val repo: StreamChatRepository
) {

  fun getTokenProvider(userId: String): TokenProvider {
    return object : TokenProvider {
      override fun loadToken() = runBlocking { repo.getUserToken(userId).token }
    }
  }

}
