package com.sebas.interrapidismo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SimpleInputText(
    modifier: Modifier = Modifier,
    labelInput: String,
    textValue: String = "",
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        value = textValue,
        onValueChange = {
            onTextChanged(it)
        } ,
        label = {
            Text(text = labelInput)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null
            )
        }
    )
}

@Composable
fun PasswordInputText(
    modifier: Modifier = Modifier,
    labelInput: String,
    textValue: String,
    onTextChanged: (String) -> Unit
) {
    val showPassword = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        value = textValue,
        onValueChange = {
            onTextChanged(it)
        } ,
        label = {
            Text(text = labelInput)
        },
        trailingIcon = {
            IconButton(onClick = {
                showPassword.value = !showPassword.value
            }) {
                Icon(
                    imageVector = if(!showPassword.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = null
                )
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Key,
                contentDescription = null
            )
        },
        visualTransformation = if(showPassword.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}