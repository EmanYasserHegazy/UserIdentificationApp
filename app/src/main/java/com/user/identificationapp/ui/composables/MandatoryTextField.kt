package com.user.identificationapp.ui.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MandatoryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    mandatoryErrorText: String = "",
    showError: Boolean = false
) {
    val isError = value.isNullOrBlank()
    if (isError && showError) {

        Text(
            text = mandatoryErrorText,
            color = Color.Red,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Start
        )

    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = placeholder,
        isError = isError && showError,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )


}
