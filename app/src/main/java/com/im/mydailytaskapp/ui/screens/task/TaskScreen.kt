package com.im.mydailytaskapp.ui.screens.task

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.im.mydailytaskapp.ui.components.ProgressBar
import com.im.mydailytaskapp.ui.utils.ViewState


@Composable
fun TaskScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    taskViewModel: TaskViewModel,
    category: String?,
) {

    val listState = rememberLazyListState()
    val context = LocalContext.current
    val tasks = taskViewModel.tasks.collectAsState().value


    category?.let {
        taskViewModel.getTasks(category = it)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {

                Column {

                    Text(
                        text = "Tasks",
                        fontSize = 28.sp,
                        modifier = Modifier.padding(top = 30.dp, start = 24.dp)
                    )

                }

                Spacer(modifier = Modifier.padding(16.dp))

                when (tasks) {

                    is ViewState.Success -> {

                        tasks.tasks?.let { TaskList(listState = listState, tasks = it) }

                    }

                    is ViewState.Error -> {

                        Toast.makeText(context, "${tasks.throwable?.message}", Toast.LENGTH_LONG)
                            .show()

                    }

                    is ViewState.Loading -> {

                        ProgressBar(isEnabled = true)

                    }

                    is ViewState.Empty -> {

                        Log.d("$category", "empty")

                    }

                }

            }
        },
        backgroundColor = Color.Transparent
    )

}