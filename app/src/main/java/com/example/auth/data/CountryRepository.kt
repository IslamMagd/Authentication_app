package com.example.auth.data

import com.example.auth.R
import com.example.auth.data.model.Country

class CountryRepository {

    fun getCountries(): List<Country> {
        return listOf(
            Country("Egypt", "+20", R.drawable.img_egypt_flag),
            Country("Saudi Arabia", "+966", R.drawable.img_saudi_arabia_flag),
            Country("United Kingdom", "+40", R.drawable.img_united_kingdom_flag),
            Country("Canada", "+1", R.drawable.img_canada_flag),
            Country("France", "+33", R.drawable.img_france_flag),
            Country("Spain", "+34", R.drawable.img_spain_flag),
            Country("Italy", "+39", R.drawable.img_italy_flag)
        )
    }
}