package com.abizer_r.friendsconnect.signup

import com.abizer_r.friendsconnect.InstantTaskExecutorExtension
import com.abizer_r.friendsconnect.domain.user.User
import com.abizer_r.friendsconnect.domain.user.UserRepository
import com.abizer_r.friendsconnect.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class CreateAnAccountTest {


    private val viewModel = SignUpViewModel(UserRepository())

    @Test
    fun accountCreated() {
        val maya = User("mayaId", "maya@friends.com", "about maya")
        viewModel.createAccount(maya.email, "Maya#1234", maya.about)
        assertEquals(SignUpState.SignedUp(maya), viewModel.signUpState.value)
    }

    @Test
    fun createAnotherAccount() {
        val bob = User("bobId", "bob@friends.com", "about bob")
        viewModel.createAccount(bob.email, "Bobb#1234", bob.about)
        assertEquals(SignUpState.SignedUp(bob), viewModel.signUpState.value)
    }


    @Test
    fun createDuplicateAccount() {
        val bob = User("bobId", "bob@friends.com", "about bob")
        val bobPassword = "Bobb#1234"
        val viewModel = SignUpViewModel(UserRepository()).also {
            it.createAccount(bob.email, bobPassword, bob.about)
        }
        viewModel.createAccount(bob.email, bobPassword, bob.about)
        assertEquals(SignUpState.DuplicateAccount, viewModel.signUpState.value)
    }
}