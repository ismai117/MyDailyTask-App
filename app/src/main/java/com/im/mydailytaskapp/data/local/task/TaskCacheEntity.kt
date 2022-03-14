package com.im.mydailytaskapp.data.local.task

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks_table")
data class TaskCacheEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val category: String,
   val date: String,
    val priority: String,
    val time: String,

    )