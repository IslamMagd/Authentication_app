package com.example.auth.presentation.screens.CountryPicker

import androidx.lifecycle.ViewModel
import com.example.auth.data.CountryRepository
import com.example.auth.data.model.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CountryPickerViewModel(private val countryRepository: CountryRepository): ViewModel() {
    private val _state = MutableStateFlow(CountryPickerUiState())
    val state = _state.asStateFlow()

    fun handleIntent(intent: CountryPickerIntent) {
        when(intent) {
            is CountryPickerIntent.LoadCountries -> loadCountries()
        }
    }

    private fun loadCountries() {
        _state.update { it.copy(isLoading = true) }
        try {
           val countries = countryRepository.getCountries()
           _state.update { it.copy(isLoading = false, countries = countries.map { it.toCountryUiState() }) }
        } catch (e: Exception) {
            _state.update { it.copy(errorMessage = e.message, isLoading = false) }
        }
    }

    fun Country.toCountryUiState(): CountryUiState {
        return CountryUiState(
            name = this.name,
            flag = this.flag,
            dialCode = this.dialCode
        )
    }

}