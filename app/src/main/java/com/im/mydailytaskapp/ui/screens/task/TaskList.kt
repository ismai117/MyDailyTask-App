package com.im.mydailytaskapp.ui.screens.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.domain.task.Task


@Composable
fun TaskList(
    listState: LazyListState,
    tasks: List<Task>,
) {

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        itemsIndexed(
            items = tasks
        ) { index, item ->

            TaskCard(item)

        }

    }


}