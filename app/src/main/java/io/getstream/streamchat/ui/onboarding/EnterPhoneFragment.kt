package io.getstream.streamchat.ui.onboarding

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import io.getstream.streamchat.R
import io.getstream.streamchat.databinding.FragmentEnterPhoneBinding
import io.getstream.streamchat.ui.Country
import io.getstream.streamchat.ui.base.BaseFragment
import io.getstream.streamchat.ui.getCountries
import io.getstream.streamchat.ui.setViewEnabled

class EnterPhoneFragment : BaseFragment(R.layout.fragment_enter_phone) {

  private lateinit var binding: FragmentEnterPhoneBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentEnterPhoneBinding.bind(view)
    binding.buttonNext.setViewEnabled(false)

    val adapter = ArrayAdapter(
      requireContext(),
      android.R.layout.simple_spinner_item,
      requireContext().getCountries()
    )

    binding.spinnerCountries.adapter = adapter
    binding.spinnerCountries.setSelection(80)

    binding.editTextPhone.addTextChangedListener {
      binding.buttonNext.setViewEnabled(it.toString().length == 10)
    }

    binding.buttonNext.setOnClickListener {

      val code = binding.spinnerCountries.selectedItem as Country
      val number = binding.editTextPhone.text.toString().trim()

      val numberWithCode = "+${code.phoneCode}$number"

      findNavController().navigate(
        R.id.verifyPhoneFragment,
        bundleOf(VerifyPhoneFragment.KEY_PHONE to numberWithCode)
      )
    }
  }
}
