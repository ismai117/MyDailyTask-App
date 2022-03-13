package com.im.mydailytaskapp.ui.screens.createtask

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.ui.utils.getColor


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowCategories(
    categories: List<Categories>,
    openDialog: MutableState<Boolean>,
    selectedItem: MutableState<String>,
) {


    val context = LocalContext.current

    FlowRow(
        modifier = Modifier
            .padding(22.dp),
        mainAxisSpacing = 4.dp,
        crossAxisSpacing = 4.dp,
    ) {

        categories.forEach { item ->

            Surface(
                modifier = Modifier
                    .padding(4.dp)
                    .selectable(
                        selected = selectedItem.value == item.category,
                        onClick = {
                            selectedItem.value = item.category
                        }
                    ),
                elevation = 4.dp,
                shape = RoundedCornerShape(20.dp),
                color = if (selectedItem.value == item.category)  Color.LightGray else Color(getColor(item.color)!!.color),
            ) {
                Text(
                    text = item.category,
                    style = MaterialTheme.typography.body1,
                    color = if (selectedItem.value == item.category) Color.Black else Color.White,
                    modifier = Modifier.padding(
                        start = 14.dp,
                        end = 14.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    )
                )

            }

        }

        Surface(
            modifier = Modifier.padding(4.dp),
            elevation = 4.dp,
            border = BorderStroke(
                width = 2.dp,
                color = Color(0xFF464646),
            ),
            shape = RoundedCornerShape(20.dp),
            color = Color.Transparent,
            onClick = {
                openDialog.value = true
            }
        ) {
            Text(
                text = "Add Category",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.padding(
                    start = 14.dp,
                    end = 14.dp,
                    top = 10.dp,
                    bottom = 10.dp
                ),
            )

        }

    }

}


