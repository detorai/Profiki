package com.example.test3.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.test4.presentation.ui.theme.SubTextDark
import com.example.test4.presentation.ui.theme.TextColor
import com.example.test4.presentation.ui.theme.hello
import com.example.test4.presentation.ui.theme.pushOrSignIn

@Composable
fun CommonLabel(
    modifier: Modifier
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            hello,
            color  = TextColor

            )
        Text(
            pushOrSignIn,
            color = SubTextDark,
            textAlign = TextAlign.Center
        )
    }
}