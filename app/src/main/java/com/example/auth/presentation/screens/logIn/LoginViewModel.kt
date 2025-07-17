package com.example.auth.presentation.screens.logIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.core.ValidationResult
import com.example.auth.domain.usecase.ValidatePhoneNumberUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _effect = Channel<LogInEffect>()
    val effect = _effect.receiveAsFlow()

    fun handleEvent(intent: LogInIntent) {
        when (intent) {
            is LogInIntent.EnterPhone -> onPhoneChange(intent.phone)
            is LogInIntent.EnterPassword -> onPasswordChange(intent.password)
            is LogInIntent.UpdateFlagRes -> updateFlagRes(intent.flagRes)
            is LogInIntent.UpdateDialCode -> updatePhoneDial(intent.dialCode)
            is LogInIntent.SubmitLogin -> validateForm()
        }
    }

    private fun onPhoneChange(phone: String) {
        _state.update { it.copy(phone = phone) }

    }

    private fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun updateFlagRes(flagRes: Int) {
        _state.update { it.copy(flagRes = flagRes) }
    }

    private fun updatePhoneDial(phoneDial: String) {
        _state.update { it.copy(dialCode = phoneDial) }
    }

    private fun validateForm() {
        val current = state.value
        val fullPhone = "${current.dialCode}${current.phone}"

        val phoneResult = validatePhoneNumberUseCase(fullPhone)

        _state.update {
            when (phoneResult) {
                is ValidationResult.Success -> it.copy(phoneError = "")
                is ValidationResult.Error -> it.copy(phoneError = phoneResult.message)
            }
        }

        if (phoneResult is ValidationResult.Success) {
            viewModelScope.launch {
                _effect.send(LogInEffect.NavigateToSignUp)
            }
        }


    }

}