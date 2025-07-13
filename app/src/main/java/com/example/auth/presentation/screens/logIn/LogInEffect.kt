package com.example.auth.presentation.screens.logIn

sealed class LogInEffect {
    object NavigateToSignUp : LogInEffect()
    object NavigateToSelectCountry: LogInEffect()
}