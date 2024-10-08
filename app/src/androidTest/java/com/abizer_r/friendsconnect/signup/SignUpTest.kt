package com.abizer_r.friendsconnect.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.abizer_r.friendsconnect.MainActivity
import com.abizer_r.friendsconnect.domain.user.repository.InMemoryUserCatalog
import com.abizer_r.friendsconnect.domain.user.repository.UserRepositoryImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpTest {

    private val duplicateUserEmail = "alice@friends.app"
    private val duplicateUserPassword = "alice#Pass123"

    @get:Rule
    val signUpTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun performSignUp() {
        launchSignUpScreen(signUpTestRule) {
            typeEmail("abizer@friends.app")
            typePassword("Valid#pass1234")
            submit()
        } verify {
            timelineScreenIsPresent()
        }
    }

    @Test
    fun displayDuplicateAccountError() {
        launchSignUpScreen(signUpTestRule) {
            typeEmail(duplicateUserEmail)
            typePassword(duplicateUserPassword)
            submit()
        } verify {
            duplicateAccountErrorIsShown()
        }
    }

}