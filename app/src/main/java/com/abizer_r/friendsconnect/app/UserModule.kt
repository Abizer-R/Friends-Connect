package com.abizer_r.friendsconnect.app

import com.abizer_r.friendsconnect.domain.user.repository.InMemoryUserCatalog
import com.abizer_r.friendsconnect.domain.user.repository.UserRepository
import com.abizer_r.friendsconnect.domain.user.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideInMemoryUserCatalog(): InMemoryUserCatalog {
        return InMemoryUserCatalog()
    }

    @Provides
    @Singleton
    fun providesUserRepository(inMemoryUserCatalog: InMemoryUserCatalog) : UserRepository {
        return UserRepositoryImpl(inMemoryUserCatalog)
    }

}