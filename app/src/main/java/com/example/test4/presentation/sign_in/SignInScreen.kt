package com.example.test4.presentation.sign_in

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.test3.presentation.common.CommonButton
import com.example.test3.presentation.common.CommonClickableText
import com.example.test3.presentation.common.CommonErrorDialog
import com.example.test3.presentation.common.CommonLabel
import com.example.test3.presentation.common.CommonText
import com.example.test3.presentation.common.CommonTextRow
import com.example.test4.presentation.home.HomeScreen
import com.example.test4.presentation.ui.theme.Accent
import com.example.test4.presentation.ui.theme.Block
import com.example.test4.presentation.ui.theme.SubTextDark
import com.example.test4.presentation.ui.theme.createUser
import com.example.test4.presentation.ui.theme.emailString
import com.example.test4.presentation.ui.theme.firstTime
import com.example.test4.presentation.ui.theme.logInString
import com.example.test4.presentation.ui.theme.passwordString
import com.example.test4.presentation.ui.theme.repair

data class SignInScreen(
    private val context: Context
): Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { SignInViewModel() }
        val state = viewModel.signInState.collectAsState().value
        val navigator = LocalNavigator.currentOrThrow
        SignIn(viewModel)
        if (state.isSignIn) navigator.push(HomeScreen(context))
    }
    @Composable
    fun SignIn(viewModel: SignInViewModel) {
        val state = viewModel.signInState.collectAsState().value

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Block)
                .padding(horizontal = 20.dp)
        ) {
            state.errorMessage?.let {
                CommonErrorDialog(
                    text = state.errorMessage!!,
                    onDismiss = { viewModel.resetError() }
                )

            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 121.dp)
            ) {
                CommonLabel(Modifier)
                CommonText(
                    modifier = Modifier.padding(top = 35.dp),
                    text = emailString,
                    state = false,
                    onTextClick = {}
                )
                CommonTextRow(
                    value = state.email,
                    onChange = viewModel::onEmail,
                    state = false,
                    visState = false,
                    modifier = Modifier.padding(top = 12.dp),
                    onPasswordVisible = {}
                )
                CommonText(
                    modifier = Modifier.padding(top = 26.dp),
                    text = passwordString,
                    state = false,
                    onTextClick = {}
                )
                CommonTextRow(
                    value = state.password,
                    onChange = viewModel::onPassword,
                    state = true,
                    visual =
                    if (!state.passwordVisible)
                        PasswordVisualTransformation()
                    else
                        VisualTransformation.None
                    ,
                    visState = state.passwordVisible,
                    modifier = Modifier.padding(top = 12.dp),
                    onPasswordVisible = {
                        viewModel.onPasswordVisibleChange()
                    }
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    val annotatedText = buildAnnotatedString {
                        pushStringAnnotation("clickable", "clickable")
                        withStyle(
                            SpanStyle(
                                color = SubTextDark
                            )
                        ) {
                            append(repair)
                        }
                    }
                    ClickableText(
                        text = annotatedText,
                        onClick = { offset ->
                            annotatedText.getStringAnnotations(offset, offset).firstOrNull()
                                ?.let { annotation ->
                                    when (annotation.item) {
                                        "clickable" -> {}
                                    }
                                }
                        }
                    )
                }
                CommonButton(
                    onClick = {
                        viewModel.auth(state.email, state.password)
                    },
                    modifier = Modifier.padding(top = 24.dp),
                    textColor = Block,
                    buttonText = logInString,
                    buttonColor = Accent
                )
            }
            CommonClickableText(
                nonClickable = firstTime,
                clickable = createUser,
                onTextClick = {},
                modifier = Modifier.padding(bottom = 50.dp)
            )
        }
    }
}
