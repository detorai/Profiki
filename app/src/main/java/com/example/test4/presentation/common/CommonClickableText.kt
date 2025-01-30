package com.example.test3.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.test4.presentation.ui.theme.Hint
import com.example.test4.presentation.ui.theme.SubTextLight

@Composable
fun CommonClickableText(
    nonClickable: String,
    clickable: String,
    modifier: Modifier,
    onTextClick: ()-> Unit
){
    val annotationText = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = SubTextLight,
            )
        ){
            append(nonClickable)
        }
        pushStringAnnotation("clickable", "clickable")
        withStyle(
            SpanStyle(
                color = Hint
            )
        ){
            append(clickable)
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        ClickableText(
            text = annotationText,
            style = TextStyle(
                lineHeight = 16.38.sp,
                textAlign = TextAlign.Center
            ),
            onClick = {offset ->
                annotationText.getStringAnnotations(offset, offset).firstOrNull()?.let { annotation ->
                    when (annotation.item) {
                        "clickable" -> onTextClick()
                    }
                }
            }
        )
    }
}