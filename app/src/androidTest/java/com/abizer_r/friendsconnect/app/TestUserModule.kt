package com.abizer_r.friendsconnect.app

import com.abizer_r.friendsconnect.domain.user.repository.TestUserRepository
import com.abizer_r.friendsconnect.domain.user.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UserModule::class]
)
abstract class TestUserModule {

    @Binds
    @Singleton
    abstract fun providesUserRepository(duplicateAccountUserRepository: TestUserRepository) : UserRepository

}