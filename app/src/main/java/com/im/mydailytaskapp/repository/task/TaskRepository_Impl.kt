package com.im.mydailytaskapp.repository.task

import com.im.mydailytaskapp.data.local.task.TaskDao
import com.im.mydailytaskapp.data.local.utils.TaskCacheEntityMapper
import com.im.mydailytaskapp.domain.task.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository_Impl
@Inject
constructor(
    private val taskDao: TaskDao,
    private val taskCacheEntityMapper: TaskCacheEntityMapper,
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return taskCacheEntityMapper.fromEntityFlowList(taskDao.getTasks())
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(taskCacheEntityMapper.toEntity(task))
    }

    override suspend fun deleteTasky(task: Task) {
        taskDao.deleteTask(taskCacheEntityMapper.toEntity(task))
    }

    override fun deleteTasks() {
        taskDao.deleteAllTasks()
    }

}