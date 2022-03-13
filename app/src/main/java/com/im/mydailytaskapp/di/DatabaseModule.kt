package com.im.mydailytaskapp.di

import androidx.room.Room
import com.im.mydailytaskapp.BaseApplication
import com.im.mydailytaskapp.data.local.categories.CategoriesDao
import com.im.mydailytaskapp.data.local.database.TaskDB
import com.im.mydailytaskapp.data.local.task.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideTaskDB(baseApplication: BaseApplication): TaskDB{

        return Room.databaseBuilder(
            baseApplication.applicationContext,
            TaskDB::class.java,
            TaskDB.TABLE_NAME
        ).fallbackToDestructiveMigration().build()

    }

    @Singleton
    @Provides
    fun provideCategoryDao(taskDB: TaskDB): CategoriesDao{

        return taskDB.getCategoriesDao()

    }

    @Singleton
    @Provides
    fun provideTaskDao(taskDB: TaskDB): TaskDao{

        return taskDB.getTaskDap()

    }


}