package com.im.mydailytaskapp.ui.screens.task

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.im.mydailytaskapp.domain.task.Task
import com.im.mydailytaskapp.repository.task.TaskRepository_Impl
import com.im.mydailytaskapp.ui.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class TaskViewModel
@Inject
constructor(
    private val taskRepositoryImpl: TaskRepository_Impl,
) : ViewModel() {

    private val _tasks = MutableStateFlow<ViewState>(ViewState.Success(emptyList()))
    val tasks: StateFlow<ViewState> = _tasks

    init {
        getTasks()
    }

    private fun getTasks() {

        viewModelScope.launch(Dispatchers.IO) {

            _tasks.value = ViewState.Loading()

            try {

                taskRepositoryImpl.getTasks().collect {

                    if (it.isNullOrEmpty()) {

                        Log.d("recipesBookmark", "empty")
                        _tasks.value = ViewState.Empty()
                    } else {
                        Log.d("recipesBookmark", "$it")
                        _tasks.value = ViewState.Success(it)
                    }

                }

            } catch (exception: Exception) {

                _tasks.value = ViewState.Error(exception)

            }

        }

    }

    fun insertTask(task: Task){
        viewModelScope.launch {
            taskRepositoryImpl.insertTask(task = task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            taskRepositoryImpl.deleteTasky(task = task)
        }
    }

}