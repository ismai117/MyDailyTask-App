package com.im.mydailytaskapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.domain.task.Task
import com.im.mydailytaskapp.ui.utils.Screen


@Composable
fun HomeTaskCategoryList(
    navController: NavController,
    task: List<Categories>,
    listState: LazyListState,
) {

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        itemsIndexed(
            items = task
        ) { index, item ->

            HomeTaskCategoryCard(
                categories = item,
                goToTaskScreen = {
                    navController.navigate(Screen.TaskScreen.route + "${item.category}")
                }
            )

        }

    }

}
