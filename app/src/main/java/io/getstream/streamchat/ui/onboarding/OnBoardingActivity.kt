package io.getstream.streamchat.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.streamchat.R

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_on_boarding)
  }
}
