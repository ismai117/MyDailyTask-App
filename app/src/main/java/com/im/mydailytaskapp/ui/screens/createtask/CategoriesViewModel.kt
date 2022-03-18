package com.im.mydailytaskapp.ui.screens.createtask

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.repository.categories.CategoriesRepository_Impl
import com.im.mydailytaskapp.ui.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class CategoriesViewModel
@Inject
constructor(
    private val categoriesRepositoryImpl: CategoriesRepository_Impl,
) : ViewModel() {

    private val _categories = MutableStateFlow<ViewState<List<Categories>>>(ViewState.Success(null))
    val categories: StateFlow<ViewState<List<Categories>>> = _categories

    val categoryTitle = mutableStateOf("")

    init {
        getCategories()
    }

    private fun getCategories() {

        viewModelScope.launch {

            _categories.value = ViewState.Loading()

            try {
                categoriesRepositoryImpl.getCategories().collect {

                    if (it.isNullOrEmpty()) {
                        _categories.value = ViewState.Empty()
                    } else {
                        _categories.value = ViewState.Success(it)
                    }

                }
            } catch (exceotion: Exception) {
                _categories.value = ViewState.Error(exceotion)
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

    fun onCategoryTitleChanged(categoryTitle: String) {
        this.categoryTitle.value = categoryTitle
    }

}