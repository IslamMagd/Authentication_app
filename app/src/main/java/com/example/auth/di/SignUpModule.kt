package com.example.auth.di

import com.example.auth.domain.usecase.ValidateEmailUseCase
import com.example.auth.domain.usecase.ValidateNameUseCase
import com.example.auth.domain.usecase.ValidatePhoneNumberUseCase
import com.example.auth.presentation.screens.signUp.SignUpViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val signUpModule = module {
    single { ValidatePhoneNumberUseCase() }
    single { ValidateNameUseCase ()}
    single { ValidateEmailUseCase() }

    viewModelOf(::SignUpViewModel)
}