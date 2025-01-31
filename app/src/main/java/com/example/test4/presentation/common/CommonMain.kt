package com.example.test4.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test4.R
import com.example.test4.presentation.ui.theme.TextColor

@Composable
fun CommonMain(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.width(142.dp).height(44.dp)
        ){
            Text(
                "Главная",
                fontWeight = FontWeight.W400,
                fontSize = 32.sp,
                lineHeight = 32.75.sp,
                color = TextColor
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.highlight_05),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 27.dp, end = 128.dp)
            )
        }
    }
}
