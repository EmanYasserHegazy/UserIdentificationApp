package com.user.identificationapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.user.identificationapp.R
import com.user.identificationapp.domain.model.Gender
import com.user.identificationapp.domain.model.User
import com.user.identificationapp.features.add_user_feature.vm.AddUserViewModel
import com.user.identificationapp.ui.composables.MandatoryTextField
import com.user.identificationapp.ui.navigation.Screen


@Composable
fun UserScreen(
    navHostController: NavHostController?,
    addUserViewModel: AddUserViewModel = hiltViewModel()
) {

    var userName by remember { mutableStateOf("") }
    var userAge by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var jobTitle by remember { mutableStateOf("") }

    //apply validation to mandatory fields
    var showUserNameError by remember { mutableStateOf(false) }
    var showJobTitleError by remember { mutableStateOf(false) }

    fun addUser() {
        addUserViewModel.updateUser(
            User(
                name = userName,
                age = userAge.takeIf { it.isNotBlank() }?.toInt() ?: 0,
                gender = (gender.takeIf { it.isNotBlank() }?.uppercase()?.let { enumValueOf(it) }
                    ?: Gender.MALE) as Gender,

                userPosition = jobTitle
            )
        )
        navHostController?.navigate(Screen.USER_LIST.name)
    }

    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState())
            .background(Color.Transparent),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = stringResource(id = R.string.add_user),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4,
            color = Color.Gray,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = stringResource(id = R.string.user_name),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(Alignment.Start),
            color = Color.Gray,
        )

        MandatoryTextField(
            userName,
            { userName = it },
            Modifier.fillMaxWidth(),
            { Text(text = "e.g. Eman Yasser") },
            stringResource(id = R.string.user_name_validation_error_mssg),
            showError = showUserNameError
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.user_age),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(Alignment.Start),
            color = Color.Gray,
        )

        TextField(
            value = userAge,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    userAge = newValue
                }
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "e.g. 20"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )

        )
        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = stringResource(id = R.string.job_title),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.Start),
            color = Color.Gray,
        )

        MandatoryTextField(
            jobTitle,
            { jobTitle = it },
            Modifier.fillMaxWidth(),
            { Text(text = "e.g. Software Engineer") },
            stringResource(id = R.string.job_title_validation_error_mssg),
            showError = showJobTitleError
        )

        Spacer(modifier = Modifier.height(16.dp))

        RadioButtonGroup(selectedOption = gender, onOptionSelected = { gender = it })

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!validateUserInputs(userName)) {
                    showUserNameError = true
                }
                if (!validateUserInputs(jobTitle)) {
                    showJobTitleError = true
                }
                if (validateUserInputs(userName) && validateUserInputs(jobTitle)) {
                    addUser()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(stringResource(id = R.string.submit))
        }
    }

}

private fun validateUserInputs(input: String): Boolean {
    return !input.isNullOrBlank()
}

@Composable
fun RadioButtonGroup(selectedOption: String, onOptionSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Gender", modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.Gray)
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalContentColor provides if (selectedOption == "Male") Color.DarkGray else Color.Transparent
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    RadioButton(
                        selected = selectedOption == "Male",
                        onClick = { onOptionSelected("Male") },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    Text(
                        "Male",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.Gray
                    )
                }
            }

            CompositionLocalProvider(
                LocalContentColor provides if (selectedOption == "Female") Color.DarkGray else Color.Transparent
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    RadioButton(
                        selected = selectedOption == "Female",
                        onClick = { onOptionSelected("Female") },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        "Female",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

