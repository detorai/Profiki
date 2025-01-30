package com.example.test3.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.test4.R
import com.example.test4.presentation.ui.theme.Background

@Composable
fun CommonTextRow(
    modifier: Modifier,
    value: String,
    onChange: (String)-> Unit,
    visual: VisualTransformation = VisualTransformation.None,
    state: Boolean,
    visState: Boolean,
    onPasswordVisible:()-> Unit
){
    BasicTextField(
        value = value,
        onValueChange = onChange,
        visualTransformation = visual,
        maxLines = 1,
        modifier = modifier.fillMaxWidth().height(48.dp),
        decorationBox = {innerText ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxWidth().height(48.dp).background(shape = RoundedCornerShape(13.dp), color = Background)
            ){
                innerText()
                if (state){
                    Icon(
                        imageVector = ImageVector.vectorResource(if (!visState) R.drawable.unvis else R.drawable.vis),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.align(Alignment.CenterEnd).padding(end = 14.dp).size(16.37.dp , 13.dp).clickable {
                            onPasswordVisible()
                        }
                    )
                }
            }
        }
    )
}