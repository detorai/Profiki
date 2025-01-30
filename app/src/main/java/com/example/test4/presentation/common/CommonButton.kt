package com.example.test3.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CommonButton(
    modifier: Modifier,
    onClick: ()-> Unit,
    textColor: Color,
    buttonColor: Color,
    buttonText: String
){
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonColors(
            contentColor = textColor,
            containerColor = buttonColor,
            disabledContentColor = textColor,
            disabledContainerColor = buttonColor,
        )
    ) {
        Text(
            buttonText,
        )
    }
}

