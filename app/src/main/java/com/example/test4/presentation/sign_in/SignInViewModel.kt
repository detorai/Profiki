package com.example.test4.presentation.sign_in

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.test4.domain.common.ResponseState
import com.example.test4.domain.sign_in.SignInUseCase
import com.example.test4.domain.sign_in.request.AuthRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel: ScreenModel {
    val signInState = MutableStateFlow(SignInScreenState())

    fun onEmail(email: String){
        screenModelScope.launch {
            signInState.update {
                signInState.value.copy(email = email)
            }
        }
    }
    fun onPassword(password: String){
        screenModelScope.launch {
            signInState.update {
                signInState.value.copy(password = password)
            }
        }
    }
    fun onPasswordVisibleChange(){
        screenModelScope.launch {
            signInState.update {
                signInState.value.copy(passwordVisible = !signInState.value.passwordVisible)
            }
        }
    }

    fun checkEmptyEmail(email: String):Boolean{
        if (email.isEmpty()) {
            screenModelScope.launch{
                signInState.update {
                    signInState.value.copy(errorMessage = "Email is empty")
                }
            }
            return false
        }
        return true
    }
    fun checkEmptyPassword(password: String): Boolean{
        if (password.isEmpty()) {
            screenModelScope.launch{
                signInState.update {
                    signInState.value.copy(errorMessage = "Password is empty")
                }
            }
            return false
        }
        return true
    }
    fun checkPattern(email: String): Boolean{
        val pattern = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]{3,}$"
        val regex = Regex(pattern)

        if (!regex.matches(email)) {
            screenModelScope.launch {
                signInState.update {
                    signInState.value.copy(errorMessage = "Incorrect Email")
                }
            }
            return false
        }
        return true
    }

    fun resetError(){
        screenModelScope.launch {
            signInState.update {
                signInState.value.copy(errorMessage = null)
            }
        }
    }

    private val signInUseCase = SignInUseCase()
    fun auth(email: String, password: String) {

        if (!checkEmptyEmail(email)) return
        if (!checkEmptyPassword(password)) return
        if (!checkPattern(email)) return
        val authRequest = AuthRequest(
            email = email,
            password = password
        )
        screenModelScope.launch {
            val result = signInUseCase.auth(
                authRequest
            )
            result.collect { networkResponse ->
                when(networkResponse) {
                    is ResponseState.Error -> {
                        signInState.update {
                            signInState.value.copy(errorMessage = networkResponse.error)
                        }
                    }
                    is ResponseState.NetworkError -> {
                        signInState.update {
                            signInState.value.copy(errorMessage = networkResponse.error)
                        }
                    }
                    is ResponseState.Loading -> {
                        signInState.update {
                            signInState.value.copy(isLoading = true)
                        }
                    }

                    is ResponseState.Success<*> -> {
                        signInState.update {
                            signInState.value.copy(isSignIn = true)

                        }
                    }
                }
            }
        }
    }
}