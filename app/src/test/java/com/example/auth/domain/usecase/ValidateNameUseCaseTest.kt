package com.example.auth.domain.usecase

import com.example.auth.domain.core.ValidationResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ValidateNameUseCaseTest {
    private lateinit var validateNameUseCase: ValidateNameUseCase

    @BeforeEach
    fun setUp() {
        validateNameUseCase = ValidateNameUseCase()
    }

    @Test
    fun `given valid name when call validate name use case then return success`() {
        val name = "Islam"

        val actualResult = validateNameUseCase(name)

        val expectedResult = ValidationResult.Success
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `given invalid email when call validate email use case then return error`() {
        val name = "o"

        val actualResult = validateNameUseCase(name)

        val expectedResult = ValidationResult.Success
        assertNotEquals(expectedResult, actualResult)
    }

}