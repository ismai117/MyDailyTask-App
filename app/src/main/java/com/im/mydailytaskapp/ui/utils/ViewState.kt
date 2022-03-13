package com.im.mydailytaskapp.ui.utils

import com.im.mydailytaskapp.domain.task.Task

sealed class ViewState(){

    class Success(tasks: List<Task>) : ViewState()

    class Error(throwable: Throwable) : ViewState()

    class Loading() : ViewState()

    class Empty() : ViewState()

}
