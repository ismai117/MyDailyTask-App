package com.im.mydailytaskapp.ui.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.domain.task.Task
import com.im.mydailytaskapp.ui.theme.Fonts



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeTaskCategoryCard(
    categories: Categories,
    goToTaskScreen: () -> Unit,
) {

    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 12.dp,
        onClick = {
            goToTaskScreen()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(8.dp)
        ) {
            Text(
                text = "${categories.category}",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = Fonts
                ),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }

}


@Preview
@Composable
fun ShowPreview() {

}