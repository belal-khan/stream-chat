package io.getstream.streamchat.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
  Intent(this, activity).also {
    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(it)
  }
}

fun View.setViewEnabled(isViewEnabled: Boolean = true) {
  this.isEnabled = isViewEnabled
  if (!isViewEnabled) {
    this.alpha = 0.7f
  } else {
    this.alpha = 1f
  }
}

fun Fragment.snackbar(message: String, retry: (() -> Unit)? = null) {
  Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).also {
    it.setAction("Ok") {
      retry?.invoke()
    }
  }.show()
}
