package com.example.test4

data class SignInScreenState (
    var errorMessage: String? = null,
    var isSignIn: Boolean = false,
    var email: String = "",
    var password: String = ""
)
