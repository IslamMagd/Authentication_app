package com.example.auth.presentation.screens.signUp

import com.example.auth.R

data class SignUpUiState(
    val name: String = "",
    val surName: String = "",
    val email: String = "",
    val phone: String = "",
    val dialCode: String = "+20",
    val flagRes: Int = R.drawable.img_egypt_flag,
    val phoneError: String = "",
    val nameError: String = "",
    val surNameError: String = "",
    val emailError: String = ""
) {
    val isAllFieldsFilled: Boolean
        get() = phone.isNotBlank() && email.isNotBlank() && name.isNotBlank() && surName.isNotBlank()
}
