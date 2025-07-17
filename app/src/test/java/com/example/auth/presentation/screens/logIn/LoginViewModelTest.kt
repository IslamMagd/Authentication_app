package com.example.auth.presentation.screens.logIn

import com.example.auth.R
import com.example.auth.domain.core.ValidationResult
import com.example.auth.domain.usecase.ValidatePhoneNumberUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class LoginViewModelTest {
    private lateinit var viewModel: LoginViewModel

    @Mock
    private lateinit var validatePhoneNumberUseCase: ValidatePhoneNumberUseCase

    @BeforeEach
    fun setup(){
        viewModel = LoginViewModel(validatePhoneNumberUseCase = validatePhoneNumberUseCase)
    }

    @Test
    fun `when user entered phone number then expected phone number state should not be empty`(){
        val phoneNumber = "01100181716"
        val countryCode = "+20"

        viewModel.handleEvent(LogInIntent.EnterPhone(phoneNumber,countryCode))

        assertThat(viewModel.state.value.phone).isNotEmpty()
    }

    @Test
    fun `when user entered password then expected password state should not be empty`(){
        val password = "Pass@123"

        viewModel.handleEvent(LogInIntent.EnterPassword(password))

        assertThat(viewModel.state.value.password).isNotEmpty()
    }

    @Test
    fun `when user didn't enter password then expected button should be disabled`(){
        val password = ""

        viewModel.handleEvent(LogInIntent.EnterPassword(password))

        assertThat(viewModel.state.value.isContinueButtonEnabled).isFalse()
    }

    @Test
    fun `when user didn't enter phone number then expected button should be disabled`(){
        val phoneNumber = ""
        val countryCode = "+20"

        viewModel.handleEvent(LogInIntent.EnterPhone(phoneNumber, countryCode))

        assertThat(viewModel.state.value.isContinueButtonEnabled).isFalse()
    }

    @Test
    fun `when user enter phone number and password then expected button should be enabled`()=
        runTest{
            val phoneNumber = "1206805626"
            val countryCode = "+20"
            val password = "si"

            viewModel.handleEvent(LogInIntent.EnterPhone(phoneNumber, countryCode))
            viewModel.handleEvent(LogInIntent.EnterPassword(password))

            assertThat(viewModel.state.value.isContinueButtonEnabled).isTrue()
        }

    @Test
    fun `given flag icon when call updateFlagRes then expected flag state should be updated`(){
        val flagRes = R.drawable.img_france_flag

        viewModel.handleEvent(LogInIntent.UpdateFlagRes(flagRes))

        assertThat(viewModel.state.value.flagRes).isEqualTo(flagRes)
    }

    @Test
    fun `given dial code when call updatePhoneDial then expected dial code state should be updated`(){
        val dialCode = "+20"

        viewModel.handleEvent(LogInIntent.UpdateDialCode(dialCode))

        assertThat(viewModel.state.value.dialCode).isEqualTo(dialCode)
    }

    @Test
    fun `given success result when call validate use case then navigate to SignUp Screen `()= runTest{
        Mockito.`when`(validatePhoneNumberUseCase(Mockito.anyString())).then {
            ValidationResult.Success
        }

        viewModel.handleEvent(LogInIntent.SubmitLogin)

        assertThat(viewModel.effect.first()).isInstanceOf(LogInEffect.NavigateToSignUp::class.java)
    }

    @Test
    fun `given error result when call validate use case then expected phone error state should not be empty `()= runTest{
        Mockito.`when`(validatePhoneNumberUseCase(Mockito.anyString())).then {
            ValidationResult.Error("ds")
        }

        viewModel.handleEvent(LogInIntent.SubmitLogin)

        assertThat(viewModel.state.value.phoneError).isNotEmpty()
    }

    @Test
    fun `given success result when call validate use case then expected phone error state should be empty `()= runTest{
        Mockito.`when`(validatePhoneNumberUseCase(Mockito.anyString())).then {
            ValidationResult.Success
        }

        viewModel.handleEvent(LogInIntent.SubmitLogin)

        assertThat(viewModel.state.value.phoneError).isEmpty()
    }

}