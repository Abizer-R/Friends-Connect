package com.abizer_r.friendsconnect.app

import com.abizer_r.friendsconnect.domain.user.repository.InMemoryUserCatalog
import com.abizer_r.friendsconnect.domain.user.repository.UserRepository
import com.abizer_r.friendsconnect.domain.user.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    fun providesUserRepository(inMemoryUserCatalog: InMemoryUserCatalog) : UserRepository {
        return UserRepositoryImpl(inMemoryUserCatalog)
    }

}