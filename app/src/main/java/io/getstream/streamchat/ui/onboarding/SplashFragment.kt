package io.getstream.streamchat.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.streamchat.R
import io.getstream.streamchat.databinding.FragmentSplashBinding
import io.getstream.streamchat.ui.base.BaseFragment


@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

  private lateinit var binding: FragmentSplashBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentSplashBinding.bind(view)

    binding.buttonContinue.setOnClickListener {
      findNavController().navigate(R.id.enterPhoneFragment)
    }
  }
}
