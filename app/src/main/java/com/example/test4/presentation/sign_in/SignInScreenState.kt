package com.example.test4.presentation.sign_in

data class SignInScreenState (
    var errorMessage: String? = null,
    var isLoading: Boolean = false,
    var isSignIn: Boolean = false,
    var email: String = "",
    var password: String = "",
    var passwordVisible: Boolean = false
)
