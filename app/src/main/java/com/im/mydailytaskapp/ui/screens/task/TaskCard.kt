package com.im.mydailytaskapp.ui.screens.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.im.mydailytaskapp.domain.task.Task
import com.im.mydailytaskapp.ui.theme.Fonts


@Composable
fun TaskCard(
    task: Task
) {

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 12.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(8.dp)
        ) {
            Text(
                text = "${task.title}",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = Fonts
                ),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterStart)
            )
            Text(
                text = "${task.time}",
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = Fonts
                ),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
            )
        }
    }

}

