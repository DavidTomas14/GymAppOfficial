package com.example.gymappofficial.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.gymappofficial.R
import com.example.gymappofficial.presentation.ui.util.TestTags.PASSWORD_TOGGLE
import com.example.gymappofficial.presentation.ui.util.TestTags.STANDARD_TEXT_FIELD

@Composable
fun StandardTextField(
    modifierTextField: Modifier= Modifier,
    modifierText: Modifier= Modifier,
    text: String = "",
    hint: String = "",
    maxLength: Int = 15,
    maxLines: Int = 1,
    color: Color,
    error: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    radiusPercent: Float = 30f,
    isPasswordVisible: Boolean = false,
    onValueChange: (String) -> Unit,
    onToggleClickChange: ((Boolean) -> Unit)? = null

) {

    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        TextField(
            value = text,
            maxLines = maxLines,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body1,
                    modifier = modifierText
                )
            },
            visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                PasswordVisualTransformation()
            }else{
                VisualTransformation.None
            },
            isError = error != "",
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            singleLine = true,
            trailingIcon = {
                if (isPasswordToggleDisplayed && onToggleClickChange != null) {
                    IconButton(
                        onClick = {
                            onToggleClickChange(!isPasswordVisible)
                        },
                        modifier = Modifier
                            .semantics {
                                testTag = PASSWORD_TOGGLE
                            }
                    ) {
                        Icon(
                            imageVector = if (isPasswordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            contentDescription = if (isPasswordVisible) {
                                stringResource(id = R.string.password_visible_content_description)
                            } else {
                                stringResource(id = R.string.password_hidden_content_description)
                            }
                        )
                    }
                }

            },
            modifier = modifierTextField
                .fillMaxWidth()
                .background(color)
                .semantics {
                    testTag = STANDARD_TEXT_FIELD
                },
            shape = RoundedCornerShape(radiusPercent)
        )

        if (error.isNotEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    
}