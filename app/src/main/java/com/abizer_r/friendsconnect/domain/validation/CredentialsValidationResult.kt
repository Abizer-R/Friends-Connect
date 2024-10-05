package com.abizer_r.friendsconnect.domain.validation

sealed class CredentialsValidationResult {
    data object InvalidEmail: CredentialsValidationResult()
    data object InvalidPassword: CredentialsValidationResult()
    data object Valid: CredentialsValidationResult()
}