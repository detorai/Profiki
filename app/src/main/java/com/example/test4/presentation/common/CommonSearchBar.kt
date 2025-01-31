package com.example.test4.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test4.R
import com.example.test4.presentation.ui.theme.Block
import com.example.test4.presentation.ui.theme.Hint

@Composable
fun CommonSearchBar(
    inputText: String,
    onValue: (String)-> Unit,
    placeholder: String,
    modifier: Modifier,
    state: Boolean
){
    BasicTextField(
        value = inputText,
        modifier = modifier.fillMaxWidth(0.8f).wrapContentHeight(),
        onValueChange = onValue,
        decorationBox = {
            Box(
                modifier = Modifier
                    .height(52.dp)
                    .background(
                        shape = RoundedCornerShape(14.dp),
                        color = Block
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.searhc_icon),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 26.dp)
                )
                Text(
                    text = inputText.ifEmpty {
                        placeholder
                    },
                    maxLines = 1,
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 62.dp),
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    color = Hint,
                )
                if (state){
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 15.dp)
                            .size(26.dp, 24.dp)

                    ){
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.microphone),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                }
            }
        }
    )
}