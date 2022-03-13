package com.im.mydailytaskapp.data.local.task

import androidx.room.*
import com.im.mydailytaskapp.data.local.categories.CategoriesCacheEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks_table")
    fun getTasks(): Flow<List<TaskCacheEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskCacheEntity: TaskCacheEntity)

    @Delete
    suspend fun deleteTask(taskCacheEntity: TaskCacheEntity)

    @Query("DELETE FROM tasks_table")
    fun deleteAllTasks()

}