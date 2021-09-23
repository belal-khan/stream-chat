package io.getstream.streamchat.ui

import android.content.Context
import io.getstream.streamchat.R

data class Country(
  val phoneCode: Int,
  val countryCode: String
) {
  override fun toString() = countryCode
}

fun Context.getCountries(): List<Country> {
  val countries = mutableListOf<Country>()
  resources.getStringArray(R.array.countries).forEach {
    val str = it.split(",")
    countries.add(Country(str[0].toInt(), str[1]))
  }
  return countries
}
