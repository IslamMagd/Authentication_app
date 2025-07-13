package com.example.auth.presentation.screens.logIn

sealed class LogInIntent {
    data class EnterPhone(val phone: String, val countryCode: String): LogInIntent()
    data class EnterPassword(val password: String): LogInIntent()
    data class UpdateDialCode(val dialCode: String): LogInIntent()
    data class UpdateFlagRes(val flagRes: Int): LogInIntent()
    object SubmitLogin : LogInIntent()
}