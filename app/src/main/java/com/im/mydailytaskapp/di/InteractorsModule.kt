package com.im.mydailytaskapp.di

import com.im.mydailytaskapp.data.local.utils.CategoriesCacheEntityMapper
import com.im.mydailytaskapp.data.local.utils.TaskCacheEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideCategoriesEntityMapper(): CategoriesCacheEntityMapper{
        return CategoriesCacheEntityMapper()
    }

    @Singleton
    @Provides
    fun provideTaskEntityMapper(): TaskCacheEntityMapper{
        return TaskCacheEntityMapper()
    }

}