package com.abizer_r.friendsconnect.domain.user

import android.util.Log
import com.abizer_r.friendsconnect.domain.exceptions.DuplicateAccountException
import com.abizer_r.friendsconnect.signup.state.SignUpState

class UserRepository(
    val inMemoryUserCatalog: InMemoryUserCatalog = InMemoryUserCatalog(),
    val num: Int = -1
) {


    fun signUp(
        email: String,
        password: String,
        about: String
    ): SignUpState {
        return try {
            val user = createUser(email, password, about)
            SignUpState.SignedUp(user)
        } catch (e: DuplicateAccountException) {
            SignUpState.DuplicateAccount
        }
    }

    private fun createUser(email: String, password: String, about: String): User {
        Log.e("TEST_repo", "createUser: num = $num", )
        if (inMemoryUserCatalog.contains(email)) {
            throw DuplicateAccountException()
        } else inMemoryUserCatalog.add(email)

        val userId = email.takeWhile { it != '@' } + "Id"
        return User(userId, email, about)
    }
}

class InMemoryUserCatalog {
    private val emailSet: MutableSet<String> = hashSetOf()
    fun contains(email: String) = emailSet.contains(email)
    fun add(email: String) { emailSet.add(email) }
}