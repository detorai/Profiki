package com.example.test4.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.test4.R
import com.example.test4.presentation.ui.theme.TextColor

@Composable
fun CommonTopBar(
    modifier: Modifier,
    onBack: ()-> Unit,
    label: String,
    onFavourite: ()-> Unit,
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CommonButtonWithIcon(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.CenterStart),
                    icon = R.drawable.arrow_back
                )
                Text(
                    label,
                    color = TextColor,
                )
                CommonButtonWithIcon(
                    onClick = onFavourite,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    icon = R.drawable.heart
                )
            }
        }
    }
}