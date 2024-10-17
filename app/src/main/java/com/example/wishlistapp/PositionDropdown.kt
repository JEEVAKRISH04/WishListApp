//package com.example.wishlistapp
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.DropdownMenu
//import androidx.compose.material.DropdownMenuItem
//import androidx.compose.material.OutlinedTextField
//import androidx.compose.material.Text
//import androidx.compose.material.TextFieldDefaults
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun PositionDropdown(
//    selectedPosition: String,
//    onPositionSelected: (String) -> Unit
//) {
//    val positions = listOf("Intern", "Trainee", "Worker", "Team Leader", "Manager")
//    var expanded by remember { mutableStateOf(false) }
//
//    Column {
//        Text(
//            text = "Position:",
//            modifier = Modifier.padding(8.dp),
//            style = TextStyle(fontSize = 16.sp, color = Color.Black)
//        )
//        OutlinedTextField(
//            value = selectedPosition,
//            onValueChange = { },
//            readOnly = true, // Make the TextField read-only
//            label = { Text("Select Position", color = Color.Black) },
//            modifier = Modifier.fillMaxWidth().clickable { expanded = true },
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                textColor = Color.Black,
//                focusedBorderColor = colorResource(id = R.color.black),
//                unfocusedBorderColor = colorResource(id = R.color.black),
//                cursorColor = colorResource(id = R.color.black),
//                focusedLabelColor = colorResource(id = R.color.black),
//                unfocusedLabelColor = colorResource(id = R.color.black),
//            )
//        )
//
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            positions.forEach { position ->
//                DropdownMenuItem(onClick = {
//                    onPositionSelected(position)
//                    expanded = false
//                }) {
//                    Text(position)
//                }
//            }
//        }
//    }
//}
