package com.example.gymappofficial.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.gymappofficial.R

@Composable
fun StandardTextField(
    text: String = "",
    hint: String = "",
    maxLength: Int = 15,
    color: Color,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    radiusPercent: Float = 30f,
    onValueChange: (String) -> Unit
) {

    val isPasswordToggleDisplayed by remember {
        mutableStateOf(keyboardType == KeyboardType.Password)
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
        value = text,
        onValueChange = {
            if (it.length < maxLength) {
                onValueChange(it)
            }
        },
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1
            )
        },
        visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
            PasswordVisualTransformation()
        }else{
             VisualTransformation.None
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        trailingIcon = {
            if (isPasswordToggleDisplayed) {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        }, contentDescription = if (isPasswordVisible) {
                            stringResource(id = R.string.password_visible_content_description)
                        } else {
                            stringResource(id = R.string.password_hidden_content_description)
                        }
                    )
                }
            }

        },
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .semantics {
                       testTag = "standard_text_field"
            },
        shape = RoundedCornerShape(radiusPercent)
    )
}