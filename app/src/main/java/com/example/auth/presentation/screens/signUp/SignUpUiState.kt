package com.example.auth.presentation.screens.signUp

data class SignUpUiState(
    val name: String = "",
    val surName: String = "",
    val email: String = "",
    val phone: String = "",
    val dialCode: String? = "+20",
    val phoneError: String = "",
    val nameError: String = "",
    val surNameError: String = "",
    val emailError: String = ""
)
