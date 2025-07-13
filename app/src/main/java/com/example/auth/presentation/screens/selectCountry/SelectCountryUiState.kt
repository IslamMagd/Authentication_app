package com.example.auth.presentation.screens.selectCountry

data class SelectCountryUiState(
    val categories: List<CountryUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class CountryUiState(
    val name: String,
    val flag: Int,
    val dialCode: String
)
