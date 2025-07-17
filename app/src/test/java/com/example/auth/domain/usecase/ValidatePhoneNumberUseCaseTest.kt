package com.example.auth.domain.usecase

import com.example.auth.domain.core.ValidationResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ValidatePhoneNumberUseCaseTest {
    private lateinit var validatePhoneNumberUseCase: ValidatePhoneNumberUseCase

    @BeforeEach
    fun setUp() {
        validatePhoneNumberUseCase = ValidatePhoneNumberUseCase()
    }

    @Test
    fun `given valid name when call validate name use case then return success`() {
        val phone = "+201206805626"

        val actualResult = validatePhoneNumberUseCase(phone)

        val expectedResult = ValidationResult.Success
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `given invalid email when call validate email use case then return error`() {
        val phoneNumber = "87334"

        val actualResult = validatePhoneNumberUseCase(phoneNumber)

        val expectedResult = ValidationResult.Success
        assertNotEquals(expectedResult, actualResult)
    }
}
