package com.im.mydailytaskapp.data.local.categories

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories_table")
    fun getCategories(): Flow<List<CategoriesCacheEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoriesEntity: CategoriesCacheEntity)

    @Delete
    suspend fun deleteCategory(categoriesEntity: CategoriesCacheEntity)

    @Query("DELETE FROM categories_table")
    fun deleteAllCategories()

}