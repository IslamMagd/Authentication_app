package com.example.auth.presentation.screens.CountryPicker

data class CountryPickerUiState(
    val countries: List<CountryUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class CountryUiState(
    val name: String,
    val dialCode: String,
    val flag: Int
)
