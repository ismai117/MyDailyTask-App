package com.im.mydailytaskapp.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.im.mydailytaskapp.ui.screens.createtask.CategoriesViewModel
import com.im.mydailytaskapp.ui.screens.task.TaskViewModel
import com.im.mydailytaskapp.ui.theme.Fonts
import com.im.mydailytaskapp.ui.utils.Screen
import com.im.mydailytaskapp.ui.utils.ViewState


@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    categoriesViewModel: CategoriesViewModel,
    taskViewModel: TaskViewModel,
) {

    val listState = rememberLazyListState()
    val categories = categoriesViewModel.categories.collectAsState().value
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {

            FloatingActionButton(
                shape = CircleShape,
                backgroundColor = Color.Black,
                contentColor = Color.White,
                onClick = {
                    navController.navigate(Screen.CreateTaskScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }

        },
        content = {

            Column(
                modifier = Modifier.fillMaxSize(),
            ) {

                Column {
                    Text(
                        text = "Today's Schedule",
                        fontSize = 28.sp,
                        color = Color.Black,
                        fontFamily = Fonts,
                        modifier = Modifier.padding(top = 60.dp, start = 24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.padding(16.dp))

                when (categories) {


                    is ViewState.Success -> {

                        categories.tasks?.let { it1 ->
                            HomeTaskCategoryList(
                                navController = navController,
                                task = it1,
                                listState = listState
                            )
                        }

                    }


                    is ViewState.Error -> {

                        Toast.makeText(context,
                            "${categories.throwable?.message}",
                            Toast.LENGTH_LONG).show()

                    }


                    is ViewState.Loading -> {

                        ProgressBar(isEnabled = true)

                    }


                    is ViewState.Empty -> {


                    }


                }

            }

        }
    )


}