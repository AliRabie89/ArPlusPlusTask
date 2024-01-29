/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.di

import com.ali.task.main.repositories.postsRepository.PostsRepository
import com.ali.task.main.repositories.postsRepository.PostsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun providePostsRepository(postsRepositoryImp: PostsRepositoryImp): PostsRepository
}