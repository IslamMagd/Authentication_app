package com.example.auth.presentation.screens.logIn

import com.example.auth.R

data class LoginUiState(
    val phone: String = "",
    val password: String = "",
    val phoneError: String = "",
    val nameError: String = "",
    val dialCode: String = "+20",
    val flagRes: Int = R.drawable.img_egypt_flag
) {
    val isContinueButtonEnabled: Boolean
        get() = phone.isNotBlank() && password.isNotBlank()
}
