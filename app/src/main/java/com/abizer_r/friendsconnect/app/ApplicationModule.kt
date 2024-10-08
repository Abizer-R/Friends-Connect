package com.abizer_r.friendsconnect.app

import com.abizer_r.friendsconnect.domain.user.InMemoryUserCatalog
import com.abizer_r.friendsconnect.domain.user.UserRepository
import com.abizer_r.friendsconnect.signup.SignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<InMemoryUserCatalog> { InMemoryUserCatalog() }
    single { UserRepository(get(), num = 0) }

    viewModel { SignUpViewModel(userRepository = get()) }
}