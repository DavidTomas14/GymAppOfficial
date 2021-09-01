package com.example.gymappofficial.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gymappofficial.presentation.ui.MainActivity
import com.example.gymappofficial.presentation.ui.theme.CursorBotones
import com.example.gymappofficial.presentation.ui.util.TestTags.PASSWORD_TOGGLE
import com.example.gymappofficial.presentation.ui.util.TestTags.STANDARD_TEXT_FIELD
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StandardTextFieldTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun enterTooLongString_maxLengthNotExceeded() {

        composeTestRule.setContent {
            var text by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                StandardTextField(
                    text = text,
                    color = CursorBotones,
                    maxLength = 5,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier
                )
            }
        }
        val expectedString = "aaaaa"
        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextClearance()

        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput(expectedString)

        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .performTextInput("a")

        composeTestRule
            .onNodeWithTag(STANDARD_TEXT_FIELD)
            .assertTextEquals(expectedString)
    }

    @Test
    fun enterPassword_toggleVisibility_passwordVisible(){

        composeTestRule.setContent {
            var text by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                StandardTextField(
                    text = text,
                    color = CursorBotones,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier,
                    keyboardType = KeyboardType.Password
                )
            }
        }
        composeTestRule
            .onNodeWithTag(PASSWORD_TOGGLE)

    }

}