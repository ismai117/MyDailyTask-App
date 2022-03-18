package com.im.mydailytaskapp.ui.utils

import com.im.mydailytaskapp.domain.task.Task

sealed class ViewState<T>(
    val tasks: T? = null,
    val throwable: Throwable? = null
){

    class Success<T>(tasks: T?) : ViewState<T>(tasks)

    class Error<T>(throwable: Throwable, tasks: T? = null) : ViewState<T>(tasks, throwable)

    class Loading<T>: ViewState<T>()

    class Empty<T> : ViewState<T>()

}
