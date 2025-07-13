package com.example.auth.domain.usecase

import android.util.Patterns
import com.example.auth.domain.core.ValidationResult

class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidationResult {
        return if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Invalid email format")
        }
    }
}