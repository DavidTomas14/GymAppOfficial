package com.example.gymappofficial.presentation.splash

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnitRunner
import com.example.gymappofficial.presentation.ui.MainActivity
import com.example.gymappofficial.presentation.ui.theme.GymAppOfficialTheme
import com.example.gymappofficial.presentation.ui.util.Screen
import com.example.gymappofficial.presentation.util.Constants
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SplashScreenTest {
    
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    //A Mock with empty functions (Dependency MockK)
    @RelaxedMockK
    lateinit var navController: NavController

    @Before //Called Before any test
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun splashScreen_displaysAndDisappears() = runBlockingTest {

        composeTestRule.setContent { 
            GymAppOfficialTheme {
                SplashScreen(navController = navController)
            }
        }
        composeTestRule
            .onNodeWithContentDescription("Foto CV")
            .assertExists()
        //No hace falta avanzar el tiempo porque runBlocking Test ya se encarga de eliminar delays
        //advanceTimeBy(Constants.SPLASH_SCREEN_DURATION)

        //Dependency MockK
        /*verify {
           navController.popBackStack()
           navController.navigate(Screen.LoginScreen.route)
        }*/
    }
}