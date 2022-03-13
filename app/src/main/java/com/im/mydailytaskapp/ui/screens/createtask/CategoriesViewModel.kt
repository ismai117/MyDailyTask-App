package com.im.mydailytaskapp.ui.screens.createtask

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.repository.categories.CategoriesRepository_Impl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoriesViewModel
@Inject
constructor(
    private val categoriesRepositoryImpl: CategoriesRepository_Impl,
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Categories>>(listOf())
    val categories: StateFlow<List<Categories>> = _categories

    val categoryTitle = mutableStateOf("")

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            categoriesRepositoryImpl.getCategories().collect {
                _categories.value = it
            }
        }
    }

    fun insertCategory(categories: Categories) {
        viewModelScope.launch {
            categoriesRepositoryImpl.insertCategory(categories)
        }
    }

    fun deleteCategory(categories: Categories) {
        viewModelScope.launch {
            categoriesRepositoryImpl.deleteCategory(categories)
        }
    }

    fun deleteCategories() {
        categoriesRepositoryImpl.deleteCategories()
    }

    fun onCategoryTitleChanged(categoryTitle: String){
        this.categoryTitle.value = categoryTitle
    }

}