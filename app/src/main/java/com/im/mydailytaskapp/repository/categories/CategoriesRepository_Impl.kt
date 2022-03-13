package com.im.mydailytaskapp.repository.categories

import com.im.mydailytaskapp.data.local.categories.CategoriesDao
import com.im.mydailytaskapp.data.local.utils.CategoriesCacheEntityMapper
import com.im.mydailytaskapp.domain.categories.Categories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepository_Impl
@Inject
constructor(
    private val categoriesDao: CategoriesDao,
    private val categoriesEntityMapper: CategoriesCacheEntityMapper
) : CategoriesRepository {

    override fun getCategories(): Flow<List<Categories>> {
        return categoriesEntityMapper.fromEntityFlowList(categoriesDao.getCategories())
    }

    override suspend fun insertCategory(categories: Categories) {
        categoriesDao.insertCategory(categoriesEntityMapper.toEntity(categories))
    }

    override suspend fun deleteCategory(categories: Categories) {
        categoriesDao.deleteCategory(categoriesEntityMapper.toEntity(categories))
    }

    override fun deleteCategories() {
        categoriesDao.deleteAllCategories()
    }

}