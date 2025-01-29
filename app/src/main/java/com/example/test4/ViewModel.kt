package com.example.test4

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ViewModel: ScreenModel {
    val state = MutableStateFlow(SignInScreenState())

    fun onEmail(email: String){
        state.update {
            it.copy(email = email)
        }
    }
    fun onPassword(password: String){
        state.update {
            it.copy(password = password)
        }
    }
    fun checkEmptyEmail(email: String):Boolean{
        throw NotImplementedError()
    }
    fun checkEmptyPassword(password: String): Boolean{
        throw NotImplementedError()

    }
    fun checkPattern(email: String): Boolean{
        throw NotImplementedError()
    }

    fun resetError(){
        throw NotImplementedError()

    }

    fun auth(email: String, password: String): Boolean{
        throw NotImplementedError()
    }
}