package com.im.mydailytaskapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.im.mydailytaskapp.ui.components.NavigationGraph
import com.im.mydailytaskapp.ui.screens.createtask.CategoriesViewModel
import com.im.mydailytaskapp.ui.screens.task.TaskViewModel
import com.im.mydailytaskapp.ui.theme.MyDailyTaskAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val categoriesViewModel: CategoriesViewModel by viewModels()
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDailyTaskAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    val activity = LocalContext.current as AppCompatActivity

                    MainScreen(
                        categoriesViewModel,
                        taskViewModel,
                        activity
                    )

                }
            }
        }
    }

    @Composable
    fun MainScreen(
        categoriesViewModel: CategoriesViewModel,
        taskViewModel: TaskViewModel,
        activity: AppCompatActivity,
    ) {

        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            content = {
                NavigationGraph(
                    navController = navController,
                    scaffoldState = scaffoldState,
                    categoriesViewModel = categoriesViewModel,
                    taskViewModel = taskViewModel,
                    activity = activity,
                    context = this
                )
            }
        )


    }

}

