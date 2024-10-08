package com.abizer_r.friendsconnect.domain.user.repository

import com.abizer_r.friendsconnect.domain.exceptions.DuplicateAccountException
import com.abizer_r.friendsconnect.domain.user.User
import com.abizer_r.friendsconnect.signup.state.SignUpState
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val inMemoryUserCatalog: InMemoryUserCatalog,
): UserRepository {


    override fun signUp(
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
        if (inMemoryUserCatalog.contains(email)) {
            throw DuplicateAccountException()
        } else inMemoryUserCatalog.add(email)

        val userId = email.takeWhile { it != '@' } + "Id"
        return User(userId, email, about)
    }
}