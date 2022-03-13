package com.im.mydailytaskapp.ui.screens.createtask

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.ui.utils.ColorsList
import com.im.mydailytaskapp.ui.utils.getAllCategories
import com.im.mydailytaskapp.ui.utils.getColor

@Composable
fun CustomDialog(
    cateoriesViewModel: CategoriesViewModel,
    openDialog: MutableState<Boolean>,
) {

    val title = cateoriesViewModel.categoryTitle.value
    val colors = getAllCategories()
    val context = LocalContext.current

    val selectedColor = remember { mutableStateOf("") }


    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp),
    ) {

        Column(modifier = Modifier.wrapContentSize()) {


        Column(
            modifier = Modifier.padding(16.dp),
        ) {

            Text(text = "Category Name")

            Spacer(modifier = Modifier.padding(12.dp))

            TextField(
                value = title,
                onValueChange = { cateoriesViewModel.onCategoryTitleChanged(it) },
                label = {
                    Text(text = "Enter title")
                }
            )

        }

        FlowRow(
            modifier = Modifier
                .padding(16.dp),
            mainAxisSpacing = 9.dp,
            crossAxisSpacing = 4.dp,
        ) {

            colors.forEach { item ->


                Button(
                    onClick = {
                        selectedColor.value = item.name
                    },
                    modifier = Modifier
                        .size(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(item.color)
                    ),
                    shape = RoundedCornerShape(40.dp),
                ) {}


            }

            Spacer(modifier = Modifier.padding(24.dp))

            Row{

                Button(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (title != "" && selectedColor.value != "") {

                           val category =  Categories(
                                id = 0,
                                category = title,
                                color = selectedColor.value
                            )

                            cateoriesViewModel.insertCategory(categories = category)

                            openDialog.value = false

                        } else {
                            Toast.makeText(context,
                                "Enter title or choose color",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text("Create")
                }

            }
        }

        }

    }

}