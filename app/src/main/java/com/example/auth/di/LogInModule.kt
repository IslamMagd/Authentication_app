package com.example.auth.di

import com.example.auth.domain.usecase.ValidatePhoneNumberUseCase
import com.example.auth.presentation.screens.logIn.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    single { ValidatePhoneNumberUseCase() }
    viewModelOf(::LoginViewModel)
}