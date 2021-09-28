package io.getstream.streamchat.data.repository

import io.getstream.streamchat.data.network.TokenApi
import javax.inject.Inject

class StreamChatRepository @Inject constructor(
  private val api: TokenApi
) {
  suspend fun getUserToken(userId: String) = api.getUserToken(userId)
}
