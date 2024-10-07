package com.abizer_r.friendsconnect.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.abizer_r.friendsconnect.MainActivity
import org.junit.Rule
import org.junit.Test

class SignUpTest {

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
}