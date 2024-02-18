package com.example.test.di

import com.example.test.book.data.repository.RepositoryImpl
import com.example.test.domain.model.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}