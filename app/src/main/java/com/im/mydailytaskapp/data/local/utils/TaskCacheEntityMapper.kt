package com.im.mydailytaskapp.data.local.utils

import com.im.mydailytaskapp.data.local.categories.CategoriesCacheEntity
import com.im.mydailytaskapp.data.local.task.TaskCacheEntity
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.domain.task.Task
import com.im.mydailytaskapp.domain.util.EntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskCacheEntityMapper : EntityMapper<TaskCacheEntity, Task> {

    override fun fromEntity(entity: TaskCacheEntity): Task {
        return Task(
            id = entity.id,
            category = entity.category,
            color = entity.color,
            title =  entity.title,
            date = entity.date,
            time = entity.time,
            priority = entity.priority
        )
    }

    override fun toEntity(model: Task): TaskCacheEntity {
        return TaskCacheEntity(
            id = model.id,
            category = model.category,
            color = model.color,
            title =  model.title,
            date = model.date,
            time = model.time,
            priority =model.priority
        )
    }

    fun fromEntityList(entityList: List<TaskCacheEntity>): List<Task>{
        return entityList.map { fromEntity(it) }
    }

    fun fromEntityFlowList(entityList: Flow<List<TaskCacheEntity>>): Flow<List<Task>> {
        return entityList.map { fromEntityList(it) }
    }


}