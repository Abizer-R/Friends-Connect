package com.abizer_r.friendsconnect.signup.state

sealed class SignUpState {
    data object BadEmail: SignUpState()
    data object BadPassword: SignUpState()
    data object Valid: SignUpState()
}
