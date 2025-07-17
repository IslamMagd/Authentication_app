package com.example.auth.domain.usecase

import android.util.Patterns
import com.example.auth.domain.core.ValidationResult

class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidationResult {
        val emailRegex = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return if (emailRegex.matches(email)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Invalid email format")
        }
    }
}