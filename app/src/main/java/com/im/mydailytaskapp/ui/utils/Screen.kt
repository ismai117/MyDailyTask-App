package com.im.mydailytaskapp.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String){

    object HomeScreen : Screen(route = "home_screen")

    object SettingsScreen : Screen(route = "settings_screen")

    object CreateTaskScreen : Screen(route = "createtask_screen")

    object TaskScreen : Screen(route = "task_screen")

}
