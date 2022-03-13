package com.im.mydailytaskapp.data.local.categories

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categories_table")
data class CategoriesCacheEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: String,
    val color: String,

    )