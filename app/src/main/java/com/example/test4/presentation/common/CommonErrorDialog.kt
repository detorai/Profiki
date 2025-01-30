package com.example.test3.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.test4.presentation.ui.theme.Background
import com.example.test4.presentation.ui.theme.TextColor

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
            modifier = Modifier.fillMaxWidth().height(150.dp).background(color = Background, shape = RoundedCornerShape(16.dp))
        ){
            Text(
                text,
                textAlign = TextAlign.Center,
                color = TextColor
            )
        }
    }
}