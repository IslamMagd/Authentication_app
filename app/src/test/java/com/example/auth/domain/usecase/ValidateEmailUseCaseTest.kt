package com.example.auth.domain.usecase

import com.example.auth.domain.core.ValidationResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ValidateEmailUseCaseTest {
    private lateinit var validateEmailUseCase: ValidateEmailUseCase

    @BeforeEach
    fun setUp() {
        validateEmailUseCase = ValidateEmailUseCase()
    }

    @Test
    fun `given valid email when call validate email use case then return success`() {
        val email = "eslam@gmail.com"

        val actualResult = validateEmailUseCase(email)

        val expectedResult = ValidationResult.Success
        assertEquals(expectedResult,actualResult)
    }

    @Test
    fun `given invalid email when call validate email use case then return error`() {
        val email = "omar"

        val actualResult = validateEmailUseCase(email)

        val expectedResult = ValidationResult.Success
        assertNotEquals(expectedResult,actualResult)
    }

}