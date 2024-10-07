package com.abizer_r.friendsconnect.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abizer_r.friendsconnect.domain.user.UserRepository
import com.abizer_r.friendsconnect.domain.validation.CredentialsValidationResult
import com.abizer_r.friendsconnect.domain.validation.RegexCredentialsValidator
import com.abizer_r.friendsconnect.signup.state.SignUpState

class SignUpViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    private val credentialsValidator = RegexCredentialsValidator()

    private val _mutableSignUpState = MutableLiveData<SignUpState>(SignUpState.Default)
    val signUpState: LiveData<SignUpState> = _mutableSignUpState

    fun createAccount(
        email: String,
        password: String,
        about: String
    ) {
        val state = when (credentialsValidator.validate(email, password)) {
            CredentialsValidationResult.InvalidEmail -> SignUpState.BadEmail
            CredentialsValidationResult.InvalidPassword -> SignUpState.BadPassword
            CredentialsValidationResult.Valid -> userRepository.signUp(email, password, about)
        }
        _mutableSignUpState.value = state
    }


}
