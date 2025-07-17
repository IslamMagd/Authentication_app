package com.example.auth.presentation.screens.signUp

import com.example.auth.domain.core.ValidationResult
import com.example.auth.domain.usecase.ValidateEmailUseCase
import com.example.auth.domain.usecase.ValidateNameUseCase
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
class SignUpViewModelTest {
    private lateinit var viewModel: SignUpViewModel

    @Mock
    private lateinit var validatePhoneNumberUseCase: ValidatePhoneNumberUseCase

    @Mock
    private lateinit var validateNameUseCase: ValidateNameUseCase

    @Mock
    private lateinit var validateEmailUseCase: ValidateEmailUseCase

    @BeforeEach
    fun setup() {
        viewModel = SignUpViewModel(
            validateNameUseCase = validateNameUseCase,
            validateEmailUseCase = validateEmailUseCase,
            validatePhoneNumberUseCase = validatePhoneNumberUseCase
        )
    }

    @Test
    fun `when user enters name then state name should not be empty`() {
        val name = "Islam"

        viewModel.handleEvent(SignUpIntent.EnterName(name))

        assertThat(viewModel.state.value.name).isNotEmpty()
    }

    @Test
    fun `when user enters surname then state surname should not be empty`() {
        val surName = "Magdy"

        viewModel.handleEvent(SignUpIntent.EnterSurName(surName))

        assertThat(viewModel.state.value.surName).isEqualTo(surName)
    }

    @Test
    fun `when user enters email then state email should not be empty`() {
        val email = "islam@example.com"

        viewModel.handleEvent(SignUpIntent.EnterEmail(email))

        assertThat(viewModel.state.value.email).isEqualTo(email)
    }

    @Test
    fun `when user enters phone then state phone should not be empty`() {
        val phone = "01012345678"

        viewModel.handleEvent(SignUpIntent.EnterPhone(phone, "+20"))

        assertThat(viewModel.state.value.phone).isNotEmpty()
    }

    @Test
    fun `given all inputs are valid then navigate to SelectCountry screen`() = runTest {
        Mockito.`when`(validateNameUseCase(Mockito.anyString())).then {
            ValidationResult.Success
        }
        Mockito.`when`(validateNameUseCase(Mockito.anyString())).then {
            ValidationResult.Success
        }
        Mockito.`when`(validateEmailUseCase(Mockito.anyString())).then {
            ValidationResult.Success
        }
        Mockito.`when`(validatePhoneNumberUseCase(Mockito.anyString())).then {
            ValidationResult.Success
        }

        viewModel.handleEvent(SignUpIntent.SubmitSignUp)

        assertThat(viewModel.effect.first()).isInstanceOf(SignUpEffect.NavigateToSelectCountry::class.java)
    }

//    @Test
//    fun `given invalid phoneNumber then navigate to SelectCountry screen`() = runTest {
//        Mockito.`when`(validatePhoneNumberUseCase(Mockito.anyString())).then {
//            ValidationResult.Error("any")
//        }
//
//        viewModel.handleEvent(SignUpIntent.SubmitSignUp)
//
//        assertThat(viewModel.effect.first()).isInstanceOf(SignUpEffect.NavigateToSelectCountry::class.java)
//    }

    @Test
    fun `given invalid phone number then phone error should not be empty`() = runTest {
        Mockito.`when`(validatePhoneNumberUseCase(Mockito.anyString())).thenReturn(
            ValidationResult.Error("Invalid phone")
        )

        viewModel.handleEvent(SignUpIntent.SubmitSignUp)

        assertThat(viewModel.state.value.phoneError).isNotEmpty()
    }

    @Test
    fun `given invalid email then email error should not be empty`() = runTest {
        Mockito.`when`(validateEmailUseCase(Mockito.anyString())).thenReturn(
            ValidationResult.Error("Invalid email")
        )

        viewModel.handleEvent(SignUpIntent.SubmitSignUp)

        assertThat(viewModel.state.value.emailError).isNotEmpty()
    }

//    @Test
//    fun `given invalid name then name error should not be empty`() = runTest {
//        Mockito.`when`(validateNameUseCase(Mockito.anyString())).thenReturn(
//            ValidationResult.Error("Invalid name")
//        )
//
//        viewModel.handleEvent(SignUpIntent.SubmitSignUp)
//
//        assertThat(viewModel.state.value.name).isNotEmpty()
//    }
//
//    @Test
//    fun `given invalid surname then surname error should not be empty`() = runTest {
//        Mockito.`when`(validateNameUseCase(Mockito.anyString())).thenReturn(
//            ValidationResult.Error("Invalid surname")
//        )
//
//        viewModel.handleEvent(SignUpIntent.SubmitSignUp)
//
//        assertThat(viewModel.state.value.surName).isNotEmpty()
//    }

}