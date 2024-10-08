package com.abizer_r.friendsconnect.signup.state

import com.abizer_r.friendsconnect.domain.user.User

sealed class SignUpState {
    data object Default: SignUpState()
    data object BadEmail: SignUpState()
    data object BadPassword: SignUpState()
    data class SignedUp(val user: User): SignUpState()
    data object DuplicateAccount: SignUpState()
}
