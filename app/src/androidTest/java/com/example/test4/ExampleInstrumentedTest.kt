package com.example.test4

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.test4.presentation.sign_in.SignInScreen
import com.example.test4.presentation.sign_in.SignInViewModel
import io.github.jan.supabase.auth.status.SessionSource

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import org.junit.runner.manipulation.Ordering.Context

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

        val viewModel = SignInViewModel()
        viewModel.auth("123@123.com", "")
        compose.setContent {
            SignInScreen(LocalContext.current).SignIn(viewModel)
        }
        compose.onNodeWithText("Password is empty").assertIsDisplayed()
    }


    @Test
    fun checkDialogBeforeEmptyEmail() {

        val viewModel = SignInViewModel()
        viewModel.auth("", "")
        compose.setContent {
            SignInScreen(LocalContext.current).SignIn(viewModel)
        }
        compose.onNodeWithText("Email is empty").assertIsDisplayed()
    }
}