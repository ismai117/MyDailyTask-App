package com.im.mydailytaskapp.di

import com.im.mydailytaskapp.data.local.categories.CategoriesDao
import com.im.mydailytaskapp.data.local.task.TaskDao
import com.im.mydailytaskapp.data.local.utils.CategoriesCacheEntityMapper
import com.im.mydailytaskapp.data.local.utils.TaskCacheEntityMapper
import com.im.mydailytaskapp.repository.categories.CategoriesRepository
import com.im.mydailytaskapp.repository.categories.CategoriesRepository_Impl
import com.im.mydailytaskapp.repository.task.TaskRepository
import com.im.mydailytaskapp.repository.task.TaskRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCategoriesRepository(
        categoriesDao: CategoriesDao,
        categoriesEntityMapper: CategoriesCacheEntityMapper
    ): CategoriesRepository {
        return CategoriesRepository_Impl(
            categoriesDao,
            categoriesEntityMapper
        )
    }

    @Singleton
    @Provides
    fun provideTaskRepository(
        taskDao: TaskDao,
        taskCacheEntityMapper: TaskCacheEntityMapper
    ): TaskRepository{
        return TaskRepository_Impl(
            taskDao,
            taskCacheEntityMapper
        )
    }

}