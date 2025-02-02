package com.example.test3.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.example.test4.presentation.ui.theme.Accent
import com.example.test4.presentation.ui.theme.TextColor

@Composable
fun CommonText(
    modifier: Modifier,
    text: String,
    onTextClick:()-> Unit,
    state: Boolean
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text,
            color = TextColor,
            textAlign = TextAlign.Center
        )
        if (state){
            val annotatedText = buildAnnotatedString {
                pushStringAnnotation("clickable", "clickable")
                withStyle(
                    SpanStyle(
                        color = Accent,
                    )
                ){
                    append("Все")
                }
            }
            ClickableText(
                text = annotatedText,
                onClick = {offset ->
                    annotatedText.getStringAnnotations(offset,offset).firstOrNull()?.let { annotation ->
                        when (annotation.item){
                            "clickable" -> {onTextClick()}
                        }
                    }
                }
            )
        }
    }
}