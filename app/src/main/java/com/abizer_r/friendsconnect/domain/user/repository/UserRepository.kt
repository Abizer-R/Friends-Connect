package com.abizer_r.friendsconnect.domain.user.repository

import android.util.Log
import com.abizer_r.friendsconnect.domain.exceptions.DuplicateAccountException
import com.abizer_r.friendsconnect.domain.user.User
import com.abizer_r.friendsconnect.signup.state.SignUpState
import javax.inject.Inject

interface UserRepository {

    fun signUp(
        email: String,
        password: String,
        about: String
    ): SignUpState

}