package com.example.auth.domain.usecase

import com.example.auth.domain.core.ValidationResult

class ValidateNameUseCase {
    operator fun invoke(name: String): ValidationResult {
        return if (name.trim().length >= 2) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Name must be at least 2 characters")
        }
    }
}