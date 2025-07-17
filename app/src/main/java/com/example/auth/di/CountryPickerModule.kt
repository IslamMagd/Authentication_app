package com.example.auth.di

import com.example.auth.data.CountryRepository
import com.example.auth.presentation.screens.CountryPicker.CountryPickerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val countryPickerModule = module {
    single { CountryRepository() }
    viewModelOf(::CountryPickerViewModel)
}