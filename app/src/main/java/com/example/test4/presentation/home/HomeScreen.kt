package com.example.test4.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.test4.presentation.ui.theme.Block

class HomeScreen: Screen {
    @Composable
    override fun Content() {
        Home()
    }
    @Composable
    fun Home(){
        Box(
            modifier = Modifier.fillMaxSize().background(Block)
        ){

        }
    }
}