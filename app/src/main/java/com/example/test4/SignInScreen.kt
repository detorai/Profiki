package com.example.test4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen

class SignInScreen(): Screen {



    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { ViewModel() }
        val state = viewModel.state.collectAsState().value
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.errorMessage?.let {
                CommonErrorDialog(
                    text = state.errorMessage!!,
                    onDismiss = viewModel::resetError
                )
            }

            TextField(
                value = state.email,
                onValueChange = viewModel::onEmail
            )
            TextField(
                value = state.password,
                onValueChange = viewModel::onPassword
            )
            Button(
              onClick = { viewModel.auth(state.email, state.password) }
            ) {

            }

        }
    }
    @Composable
    fun CommonErrorDialog(
        text: String,
        onDismiss: ()-> Unit
    ){
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            ){
                Text(
                    text
                )
            }
        }
    }
}