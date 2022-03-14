package com.im.mydailytaskapp.ui.screens.createtask

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.im.mydailytaskapp.domain.categories.Categories
import com.im.mydailytaskapp.domain.task.Task
import com.im.mydailytaskapp.ui.screens.task.TaskViewModel
import com.im.mydailytaskapp.ui.theme.Fonts
import com.im.mydailytaskapp.ui.utils.Constants.High
import com.im.mydailytaskapp.ui.utils.Constants.Low
import com.im.mydailytaskapp.ui.utils.Screen
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun CreateTaskScreen(
    navController: NavController,
    categoriesViewModel: CategoriesViewModel,
    taskViewModel: TaskViewModel,
    activity: AppCompatActivity,
    context: Context,
) {

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val title = remember { mutableStateOf("") }
    val categories = categoriesViewModel.categories.collectAsState().value
    val openDialog = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CreateTaskAppBar(navController = navController)
        },
        content = {
            CreateTaskContent(
                navController,
                scrollState,
                categories,
                title,
                openDialog,
                taskViewModel,
                activity,
            )
        },
        backgroundColor = Color.Transparent,
    )

    if (openDialog.value) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = contentColorFor(MaterialTheme.colors.background)
                        .copy(alpha = 0.6f)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        openDialog.value = false
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            CustomDialog(
                categoriesViewModel,
                openDialog,
            )
        }

    }

}


@Composable
private fun CreateTaskAppBar(navController: NavController) {
    TopAppBar(
        title = {

        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    Modifier.size(28.dp)
                )
            }
        },
        backgroundColor = Color(0xFF28282B),
        contentColor = Color.LightGray,
        elevation = 0.dp
    )
}

@Composable
private fun CreateTaskContent(
    navController: NavController,
    scrollState: ScrollState,
    categories: List<Categories>,
    title: MutableState<String>,
    openDialog: MutableState<Boolean>,
    taskViewModel: TaskViewModel,
    activity: AppCompatActivity,
) {

    val selectedCategrory = remember { mutableStateOf("") }
    val selectedPriority = remember { mutableStateOf("") }

    var pickedDate: String? by remember { mutableStateOf(null) }
    var pickedTime: String? by remember { mutableStateOf(null) }

    val selectedDate = { date: Long? ->
        pickedDate = dateformatter(date)
    }

    val selectedTime = { time: String? ->
        pickedTime = time
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFF28282B)),
    ) {
        Column {
            Text(
                text = "Create",
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 28.sp,
                    fontFamily = Fonts
                ),
                modifier = Modifier.padding(start = 20.dp, top = 8.dp)
            )
            Text(
                text = "New Task",
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 28.sp,
                    fontFamily = Fonts
                ),
                modifier = Modifier.padding(start = 20.dp, top = 6.dp)
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Column {
            TextField(
                label = {
                    Text(
                        text = "Task Title",
                        color = Color.LightGray
                    )
                },
                value = title.value,
                onValueChange = { title.value = it },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.LightGray,
                    textColor = Color.LightGray,
                    focusedLabelColor = Color.LightGray,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF464646),
                    ),
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Column {
            Text(
                text = "Task type",
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
            Row(
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .width(110.dp)
                        .height(45.dp)
                        .border(
                            width = 2.dp,
                            color = Color(0xFF464646),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .selectable(
                            selected = selectedPriority.value == High,
                            onClick = {
                                selectedPriority.value = High
                            }
                        ),
                    shape = RoundedCornerShape(20.dp),
                    color = if (selectedPriority.value == High) Color.LightGray else Color.Transparent
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = High,
                            style = TextStyle(
                                color = if (selectedPriority.value == High) Color.Black else Color.LightGray
                            ),
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Surface(
                    modifier = Modifier
                        .width(110.dp)
                        .height(45.dp)
                        .border(
                            width = 2.dp,
                            color = Color(0xFF464646),
                            shape = RoundedCornerShape(20.dp),
                        )
                        .selectable(
                            selected = selectedPriority.value == Low,
                            onClick = {
                                selectedPriority.value = Low
                            }
                        ),
                    shape = RoundedCornerShape(20.dp),
                    color = if (selectedPriority.value == Low) Color.LightGray else Color.Transparent
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = Low,
                            style = TextStyle(
                                color = if (selectedPriority.value == Low) Color.Black else Color.LightGray
                            ),
                        )

                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Column {
            Text(
                text = "Choose date & time",
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)

            ) {
                Button(
                    onClick = {
                        showDatePicker(activity, selectedDate)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .weight(1f)
                        .border(
                            width = 2.dp,
                            color = Color(0xFF464646),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color.Transparent,
                    ),
                ) {
                    Text(
                        text = pickedDate ?: "Select a Date",
                        style = TextStyle(
                            color = Color.LightGray
                        )
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Button(
                    onClick = {
                        showTimePicker(activity, selectedTime)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .weight(1f)
                        .border(
                            width = 2.dp,
                            color = Color(0xFF464646),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color.Transparent,
                    )
                ) {
                    Text(
                        text = pickedTime ?: "Select Time",
                        style = TextStyle(
                            color = Color.LightGray
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Column {
            Text(
                text = "Category",
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
            ShowCategories(
                categories = categories,
                openDialog = openDialog,
                selectedItem = selectedCategrory
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {

                    if (
                        title.value == "" ||
                        selectedCategrory.value == "" ||
                        selectedPriority.value == "" ||
                        pickedDate == "" ||
                        pickedTime == ""
                    ) {
                        Toast.makeText(activity, "not created", Toast.LENGTH_LONG).show()
                    } else {

                        Toast.makeText(activity, "created", Toast.LENGTH_LONG).show()

                        Log.d("Tasks", "\n${title.value}\n${selectedCategrory.value}\n${selectedPriority.value}\n$pickedDate\n$pickedTime")

                        val task = pickedDate?.let {
                            pickedTime?.let { it1 ->
                                Task(
                                    id = 0,
                                    title = title.value,
                                    category = selectedCategrory.value,
                                    priority = selectedPriority.value,
                                    date = it,
                                    time = it1,
                                )
                            }
                        }

                        task?.let { taskViewModel.insertTask(task = it) }

                        navController.navigate(Screen.HomeScreen.route)

                    }


                },
                modifier = Modifier
                    .width(240.dp)
                    .height(45.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF464646),
                        shape = RoundedCornerShape(50.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                ),
                elevation = ButtonDefaults.elevation(4.dp)
            ) {
                Text(
                    text = "Create Task",
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 22.sp
                    )
                )
            }
        }
    }

}


fun showDatePicker(
    activity: AppCompatActivity,
    selectedDate: (Long?) -> Unit,
) {

    val picker = MaterialDatePicker.Builder.datePicker().build()

    picker.show(activity.supportFragmentManager, picker.toString())

    picker.addOnPositiveButtonClickListener {
        selectedDate(it)
    }

}

fun dateformatter(ms: Long?): String? {

    ms?.let {

        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
        val calender: Calendar = Calendar.getInstance()
        calender.setTimeInMillis(it)

        return formatter.format(calender.getTime())

    }

    return null

}

fun showTimePicker(
    activity: AppCompatActivity,
    selectedTime: (String) -> Unit,
) {


    val picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
    picker.show(activity.supportFragmentManager, picker.toString())

    picker.addOnPositiveButtonClickListener {

        val time = String.format(Locale.getDefault(), "%02d:%02d", picker.hour, picker.minute)

        var hour = picker.hour

        var am_pm = ""

        if (hour >= 12) {
            am_pm = "PM";
            hour -= 12;
        } else {
            am_pm = "AM";
        }

        selectedTime("$time $am_pm")

    }


}


