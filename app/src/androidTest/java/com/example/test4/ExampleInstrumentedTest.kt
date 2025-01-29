package com.example.test4

import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.test4.ui.theme.Test4Theme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val compose = createComposeRule()
    @Test
    fun checkDialogBeforeEmptyPassword() {

        compose.setContent {
            SignInScreen().Content()
        }
        compose.onNodeWithText("Password is empty").assertIsDisplayed()
    }


    @Test
    fun checkDialogBeforeEmptyEmail() {
        val screen = SignInScreen()
        compose.setContent {
            screen.Content()

        }
        compose.onNodeWithText("Email is empty").assertIsDisplayed()
    }
}