package com.example.gymappofficial.presentation.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gymappofficial.presentation.login.LoginScreen
import com.example.gymappofficial.presentation.ui.MainActivity
import com.example.gymappofficial.presentation.ui.theme.CursorBotones
import com.example.gymappofficial.presentation.ui.theme.GymAppOfficialTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StandardTextFieldTest {
    
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    
    @Before
    fun setUp() {
        composeTestRule.setContent { 
            GymAppOfficialTheme {
                var text by remember {
                    mutableStateOf("")
                }
                StandardTextField(
                    color = CursorBotones,
                    maxLength = 5
                ) {
                    text = it
                }

            }
        }
    }
    
    @Test
    fun enterTooLongString_maxLengthNotExceeded() {
        composeTestRule
            .onNodeWithTag("standard_text_field")
            .performTextInput("DavidTomas")

        composeTestRule
            .onNodeWithTag("standard_text_field")
            .assertTextEquals("DavidTomas")
    }

}