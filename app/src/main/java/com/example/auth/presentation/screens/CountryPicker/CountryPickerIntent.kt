package com.example.auth.presentation.screens.CountryPicker

sealed class CountryPickerIntent {
    object LoadCountries: CountryPickerIntent()
}