package com.abizer_r.friendsconnect.signup

import com.abizer_r.friendsconnect.InstantTaskExecutorExtension
import com.abizer_r.friendsconnect.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@ExtendWith(InstantTaskExecutorExtension::class)
class CredentialsValidationTest {


    private val validEmail = "valid@email.com"
    private val validPassword = "abcDEF123@#"

    @ParameterizedTest
    @CsvSource(
        "''",
        "'   '",
        "'email'",
        "'a@b'",
    )
    fun invalidEmail(email: String) {
        val viewModel = SignUpViewModel()
        viewModel.createAccount(email, validPassword, ":about:")
        assertEquals(SignUpState.BadEmail, viewModel.signUpState.value)
    }

    @ParameterizedTest
    @CsvSource(
        "''",
        "'           '",
        "'12345678'",
        "'abcd2343243'",
        "'abcd234^#'",
    )
    fun invalidPassword(password: String) {
        val viewModel = SignUpViewModel()
        viewModel.createAccount(validEmail, password, ":about:")
        assertEquals(SignUpState.BadPassword, viewModel.signUpState.value)
    }

    @Test
    fun validCredentials() {
        val viewModel = SignUpViewModel()
        viewModel.createAccount(validEmail, validPassword, ":about:")
        assertEquals(SignUpState.Valid, viewModel.signUpState.value)
    }
}