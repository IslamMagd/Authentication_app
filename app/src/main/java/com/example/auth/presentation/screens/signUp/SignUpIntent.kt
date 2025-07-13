package com.example.auth.presentation.screens.signUp

sealed class SignUpIntent {
    data class EnterName(val name: String): SignUpIntent()
    data class EnterSurName(val surName: String): SignUpIntent()
    data class EnterEmail(val email: String): SignUpIntent()
    data class EnterPhone(val phone: String, val diaCode: String): SignUpIntent()
    object SubmitSignUp: SignUpIntent()
}