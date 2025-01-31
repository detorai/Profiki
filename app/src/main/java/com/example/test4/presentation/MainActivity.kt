package com.example.test4.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import com.example.test4.presentation.home.HomeScreen
import com.example.test4.presentation.onboard.OnBoardScreen
import com.example.test4.presentation.sign_in.SignInScreen
import com.example.test4.presentation.ui.theme.Test4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Test4Theme {
                Navigator(HomeScreen())
            }
        }
    }
}

