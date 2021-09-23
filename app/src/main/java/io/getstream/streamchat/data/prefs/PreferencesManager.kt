package io.getstream.streamchat.data.prefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PreferencesManager @Inject constructor(@ApplicationContext mContext: Context) {

  private val prefs = mContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

  var isUserOnboardingFinished: Boolean
    get() = prefs.getBoolean(IS_USER_ONBOARDING_FINISHED, false)
    set(value) = prefs.edit().putBoolean(IS_USER_ONBOARDING_FINISHED, value).apply()


  companion object {
    private const val SHARED_PREFS_NAME = "prefs_name"
    private const val IS_USER_ONBOARDING_FINISHED = "is_user_onboarding_finished"
  }

}
