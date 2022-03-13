package com.im.mydailytaskapp.data.local.utils

import com.im.mydailytaskapp.data.local.categories.CategoriesCacheEntity
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.domain.util.EntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CategoriesCacheEntityMapper : EntityMapper<CategoriesCacheEntity, Categories> {

    override fun fromEntity(entity: CategoriesCacheEntity): Categories {
        return Categories(
            id = entity.id,
            category = entity.category,
            color = entity.color
        )
    }

    override fun toEntity(model: Categories): CategoriesCacheEntity {
        return CategoriesCacheEntity(
            id = model.id,
            category = model.category,
            color = model.color
        )
    }

    fun fromEntityList(entityList: List<CategoriesCacheEntity>): List<Categories>{
        return entityList.map { fromEntity(it) }
    }

    fun fromEntityFlowList(entityList: Flow<List<CategoriesCacheEntity>>): Flow<List<Categories>>{
        return entityList.map { fromEntityList(it) }
    }


}