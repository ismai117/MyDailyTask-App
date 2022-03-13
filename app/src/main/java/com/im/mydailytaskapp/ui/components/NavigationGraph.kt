package com.im.mydailytaskapp.ui.components

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.im.mydailytaskapp.ui.screens.createtask.CategoriesViewModel
import com.im.mydailytaskapp.ui.screens.createtask.CreateTaskScreen
import com.im.mydailytaskapp.ui.screens.home.HomeScreen
import com.im.mydailytaskapp.ui.screens.settings.SettingsScreen
import com.im.mydailytaskapp.ui.screens.task.TaskViewModel
import com.im.mydailytaskapp.ui.utils.Screen


@Composable
fun NavigationGraph(
    navController: NavController,
    scaffoldState: ScaffoldState,
    categoriesViewModel: CategoriesViewModel,
    taskViewModel: TaskViewModel,
    activity: AppCompatActivity,
    context: Context,
) {

    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.HomeScreen.route
    ) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                scaffoldState = scaffoldState,
                taskViewModel = taskViewModel
            )
        }

        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen()
        }

        composable(route = Screen.CreateTaskScreen.route) {
            CreateTaskScreen(
                navController = navController,
                categoriesViewModel = categoriesViewModel,
                taskViewModel = taskViewModel,
                activity = activity,
                context = context
            )
        }

    }

}