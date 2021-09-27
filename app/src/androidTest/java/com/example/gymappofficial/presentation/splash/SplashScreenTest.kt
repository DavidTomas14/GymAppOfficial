package com.example.gymappofficial.presentation.splash

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gymappofficial.core.presentation.MainActivity
import com.example.gymappofficial.core.presentation.ui.theme.GymAppOfficialTheme
import com.example.gymappofficial.core.util.Screen
import com.example.gymappofficial.core.util.Constants
import com.example.gymappofficial.feature_auth.presentation.splash.SplashScreen
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
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

    private val testDispatcher = TestCoroutineDispatcher()

    @Before //Called Before any test
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun splashScreen_displaysAndDisappears() = testDispatcher.runBlockingTest {

        composeTestRule.setContent { 
            GymAppOfficialTheme {
                SplashScreen(
                    navController = navController,
                    dispatcher = testDispatcher
                )

            }
        }
        composeTestRule
            .onNodeWithContentDescription("Foto CV")
            .assertExists()
        //No hace falta avanzar el tiempo porque runBlocking Test ya se encarga de eliminar delays
        //advanceTimeBy(Constants.SPLASH_SCREEN_DURATION)

        advanceTimeBy(Constants.SPLASH_SCREEN_DURATION)

        //Dependency MockK
        verify {
           navController.popBackStack()
           navController.navigate(Screen.LoginScreen.route)
        }
    }
}