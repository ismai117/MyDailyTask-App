package com.im.mydailytaskapp.domain.task

import java.util.*


data class Task(

    val id: Int,
    val title: String,
    val category: String,
    val priority: String,
    val date: String,
    val time: String,

    )