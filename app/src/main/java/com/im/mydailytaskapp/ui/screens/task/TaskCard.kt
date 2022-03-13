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
import com.im.mydailytaskapp.ui.theme.Fonts


@Composable
fun TaskCard(

) {

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Text(
                text = "Gym Session",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = Fonts
                ),
                modifier = Modifier
                    .padding(top = 8.dp, start = 12.dp)
                    .align(Alignment.TopStart)
            )
            Text(
                text = "10:00 AM",
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = Fonts
                ),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 12.dp)
            )
            Text(
                text = "Hoteps BEEN Told You 194 - Bryce Mitchell ",
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = Fonts
                ),
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 50.dp, start = 12.dp, bottom = 8.dp)
            )
        }
    }

}


@Preview
@Composable
fun ShowDefault() {
    TaskCard()
}