package com.abizer_r.friendsconnect.app

import com.abizer_r.friendsconnect.domain.user.UserRepository
import com.abizer_r.friendsconnect.signup.SignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { UserRepository() }

    viewModel<SignUpViewModel> {
        SignUpViewModel(userRepository = get())
    }
}