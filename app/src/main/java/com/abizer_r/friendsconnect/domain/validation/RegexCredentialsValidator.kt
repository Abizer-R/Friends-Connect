package com.abizer_r.friendsconnect.domain.validation

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern

class RegexCredentialsValidator {

    companion object {
        const val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    }

    private val emailPattern: Pattern = PatternsCompat.EMAIL_ADDRESS
    private val passwordPattern: Pattern = Pattern.compile(passwordRegex)

    fun validate(email: String, password: String): CredentialsValidationResult {
        return if (isValidEmail(email).not()) {
            CredentialsValidationResult.InvalidEmail
        } else if (isValidPassword(password).not()) {
            CredentialsValidationResult.InvalidPassword
        } else CredentialsValidationResult.Valid
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && emailPattern.matcher(email).matches()
    }

    private fun isValidPassword(password: String) : Boolean {
        return password.isNotBlank() && passwordPattern.matcher(password).matches()
    }

}