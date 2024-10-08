package com.abizer_r.friendsconnect.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abizer_r.friendsconnect.MainActivity
import com.abizer_r.friendsconnect.domain.user.repository.TestUserRepository.Companion.duplicateUserEmail
import com.abizer_r.friendsconnect.domain.user.repository.TestUserRepository.Companion.duplicateUserPassword
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class SignUpTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val signUpTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }


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