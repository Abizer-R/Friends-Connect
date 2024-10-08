package com.abizer_r.friendsconnect.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.abizer_r.friendsconnect.MainActivity
import com.abizer_r.friendsconnect.domain.user.InMemoryUserCatalog
import com.abizer_r.friendsconnect.domain.user.UserRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get

class SignUpTest {

    private val duplicateUserEmail = "alice@friends.app"
    private val duplicateUserPassword = "alice#Pass123"

    @get:Rule
    val signUpTestRule = createAndroidComposeRule<MainActivity>()

//    private val emailSet: HashSet<String> = hashSetOf()
    private val inMemoryUserCatalog = InMemoryUserCatalog()

    private val signUpModule = module {
        single<InMemoryUserCatalog> { inMemoryUserCatalog }
        single { UserRepository(get(), num = 1) }

        viewModel { SignUpViewModel(userRepository = get()) }
    }

    @Before
    fun setUp() {
        // Add the signed-up user email to the catalog
        inMemoryUserCatalog.add(duplicateUserEmail)

        // Stop Koin before starting it to ensure a clean state
        stopKoin()
        // Load only the test module
        startKoin {
            modules(signUpModule)
        }
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
//        assertSameInstanceOfUserCatalog()
        launchSignUpScreen(signUpTestRule) {
            typeEmail(duplicateUserEmail)
            typePassword(duplicateUserPassword)
            submit()
        } verify {
            duplicateAccountErrorIsShown()
        }
    }

    private fun assertSameInstanceOfUserCatalog() {

        // Verify that the instance of InMemoryUserCatalog is the same
        val userRepository = get<UserRepository>(UserRepository::class.java)
        val catalogFromRepo = (userRepository as UserRepository).inMemoryUserCatalog
        assert(inMemoryUserCatalog === catalogFromRepo) { "Different instances of InMemoryUserCatalog" }
    }
}