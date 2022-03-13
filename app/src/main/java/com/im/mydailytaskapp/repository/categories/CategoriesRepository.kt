package com.im.mydailytaskapp.repository.categories

import com.im.mydailytaskapp.domain.categories.Categories
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {


    fun getCategories(): Flow<List<Categories>>

    suspend fun insertCategory(categories: Categories)

    suspend fun deleteCategory(categories: Categories)

    fun deleteCategories()

}