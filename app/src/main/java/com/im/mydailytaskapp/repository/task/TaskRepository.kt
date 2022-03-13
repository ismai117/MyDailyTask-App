package com.im.mydailytaskapp.repository.task

import com.im.mydailytaskapp.domain.task.Task
import kotlinx.coroutines.flow.Flow


interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    suspend fun insertTask(task: Task)

    suspend fun deleteTasky(task: Task)

    fun deleteTasks()

}