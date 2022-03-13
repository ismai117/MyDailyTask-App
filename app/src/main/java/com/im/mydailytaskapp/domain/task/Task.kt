package com.im.mydailytaskapp.domain.task

import java.util.*


data class Task(

    val id: Int,
    val title: String,
    val category: String,
    val color: String,
    val date: Date,
    val priority: String,
    val time: String,

    )