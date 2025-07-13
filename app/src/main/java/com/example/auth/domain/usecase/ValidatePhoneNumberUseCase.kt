package com.example.auth.domain.usecase

import com.example.auth.domain.core.ValidationResult
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil

class ValidatePhoneNumberUseCase {
    operator fun invoke(fullPhone: String): ValidationResult {
        val phoneUtil = PhoneNumberUtil.getInstance()
        return try {
            val numberProto = phoneUtil.parse(fullPhone, null)
            val isValid = phoneUtil.isValidNumber(numberProto)

            if (isValid) ValidationResult.Success
            else ValidationResult.Error("Invalid phone number")
        } catch (e: NumberParseException) {
            ValidationResult.Error("Invalid phone format")
        }
    }
}

