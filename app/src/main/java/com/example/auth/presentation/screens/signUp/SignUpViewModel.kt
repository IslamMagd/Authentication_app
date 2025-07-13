package com.example.auth.presentation.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.core.ValidationResult
import com.example.auth.domain.usecase.ValidateEmailUseCase
import com.example.auth.domain.usecase.ValidateNameUseCase
import com.example.auth.domain.usecase.ValidatePhoneNumberUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpUiState())
    val state = _state.asStateFlow()

    private val _effect = Channel<SignUpEffect>()
    val effect = _effect.receiveAsFlow()

    fun handleEvent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.EnterName -> onNameChange(intent.name)
            is SignUpIntent.EnterSurName -> onSurNameChange(intent.surName)
            is SignUpIntent.EnterEmail -> onEmailChange(intent.email)
            is SignUpIntent.EnterPhone -> onPhoneChange(intent.phone)
            is SignUpIntent.SubmitSignUp -> validateInputs()
        }
    }

    private fun onNameChange(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun onSurNameChange(surName: String) {
        _state.update { it.copy(surName = surName) }
    }

    private fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    private fun onPhoneChange(phone: String) {
        _state.update { it.copy(phone = phone) }
    }

    private fun validateInputs() {
        val current = state.value
        val fullPhone = "${current.dialCode}${current.phone}"

        val phoneResult = validatePhoneNumberUseCase(fullPhone)
        val nameResult = validateNameUseCase(current.name)
        val surNameResult = validateNameUseCase(current.surName)
        val emailResult = validateEmailUseCase(current.email)

        val hasError =
            nameResult is ValidationResult.Error
                    || emailResult is ValidationResult.Error
                    || phoneResult is ValidationResult.Error
                    || surNameResult is ValidationResult.Error

        _state.update {
            it.copy(
                phoneError = (phoneResult as? ValidationResult.Error)?.message.orEmpty(),
                nameError = (nameResult as? ValidationResult.Error)?.message.orEmpty(),
                emailError = (emailResult as? ValidationResult.Error)?.message.orEmpty(),
                surNameError = (surNameResult as? ValidationResult.Error)?.message.orEmpty(),

            )
        }

        if (!hasError) {
            viewModelScope.launch {
                _effect.send(SignUpEffect.NavigateToSelectCountry)
            }
        }
    }

}