package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {

    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if (id != 0L) {
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.wishnameState = wish.value.name
        viewModel.wishpositionState = wish.value.position
        viewModel.wishcontactNumberState = wish.value.contactNumber
        viewModel.wishgendersState = wish.value.genders
        viewModel.wishDepartmentsState = wish.value.Department
    } else {
        viewModel.wishnameState = ""
        viewModel.wishpositionState = ""
        viewModel.wishcontactNumberState = ""
        viewModel.wishgendersState = "Male"
        viewModel.wishDepartmentsState = ""
    }

    Scaffold(topBar = {
        AppBarView(title =
        if (id != 0L) stringResource(id = R.string.update_wish)
        else stringResource(id = R.string.add_wish)
        ) { navController.navigateUp() }
    },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Name",
                value = viewModel.wishnameState,
                onValueChanged = { viewModel.onWishnameChanged(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

//            PositionDropdown(
//                selectedPosition = viewModel.wishpositionState,
//                onPositionSelected = { position -> viewModel.onWishpositionChanged(position) }
//            )


            WishTextField(
                label = "Position",
                value = viewModel.wishpositionState,
                onValueChanged = { viewModel.onWishpositionChanged(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))


            WishTextField(
                label = "Contact Number",
                value = viewModel.wishcontactNumberState,
                onValueChanged = { viewModel.onWishcontactNumberChanged(it) },
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(10.dp))


            GenderSelection(
                selectedGender = viewModel.wishgendersState,
                onGenderSelected = { gender -> viewModel.onWishgendersChanged(gender) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Department",
                value = viewModel.wishDepartmentsState,
                onValueChanged = {
                    viewModel.onWishDepartmentsChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if (viewModel.wishnameState.isNotEmpty() &&
                    viewModel.wishpositionState.isNotEmpty() &&
                    viewModel.wishcontactNumberState.isNotEmpty() &&
                    viewModel.wishgendersState.isNotEmpty() &&
                    viewModel.wishDepartmentsState.isNotEmpty()
                ) {
                    if (id != 0L) {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                name = viewModel.wishnameState.trim(),
                                position = viewModel.wishpositionState.trim(),
                                contactNumber = viewModel.wishcontactNumberState.trim(),
                                genders = viewModel.wishgendersState.trim(),
                                Department = viewModel.wishDepartmentsState.trim()
                            )
                        )
                    } else {
                        viewModel.addWish(
                            Wish(
                                name = viewModel.wishnameState.trim(),
                                position = viewModel.wishpositionState.trim(),
                                contactNumber = viewModel.wishcontactNumberState.trim(),
                                genders = viewModel.wishgendersState.trim(),
                                Department = viewModel.wishDepartmentsState.trim()
                            )
                        )
                        snackMessage.value = "Data has been Created"
                    }
                } else {
                    snackMessage.value = "Enter details to create data"
                }

                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            }) {
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_wish)
                    else stringResource(id = R.string.add_wish),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}



@Composable
fun GenderSelection(selectedGender: String, onGenderSelected: (String) -> Unit) {
    Column {
        Text("Gender:")
        Row {
            RadioButton(
                selected = selectedGender == "Male",
                onClick = { onGenderSelected("Male") }
            )
            Text("Male")
            RadioButton(
                selected = selectedGender == "Female",
                onClick = { onGenderSelected("Female") }
            )
            Text("Female")
        }
    }
}




@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text // Change: Added keyboardType parameter
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType), // Change: Used keyboardType here
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )
    )
}

@Preview
@Composable
fun WishTestFieldPrev() {
    WishTextField(label = "text", value = "text", onValueChanged = {})
}
