package io.getstream.streamchat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.getstream.chat.android.client.ChatClient

@HiltAndroidApp
class StreamChatApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    ChatClient.Builder("uc2v6hj6kcu6", applicationContext).build()
  }
}
