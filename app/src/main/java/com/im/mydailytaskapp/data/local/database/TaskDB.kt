package com.im.mydailytaskapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.im.mydailytaskapp.data.local.categories.CategoriesDao
import com.im.mydailytaskapp.data.local.categories.CategoriesCacheEntity
import com.im.mydailytaskapp.data.local.task.TaskCacheEntity
import com.im.mydailytaskapp.data.local.task.TaskDao

@Database(
    entities = [CategoriesCacheEntity::class, TaskCacheEntity::class],
    version = 5,
    exportSchema = false
)
abstract class TaskDB : RoomDatabase() {

    abstract fun getCategoriesDao(): CategoriesDao
    abstract fun getTaskDap(): TaskDao

    companion object {

        val TABLE_NAME = "categories_table"

    }

}