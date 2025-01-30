package com.example.test4.domain.common

sealed class ResponseState {
    class NetworkError(val error: String): ResponseState()
    class Error(val error: String): ResponseState()
    class Success<T>(val data: T): ResponseState()
    class Loading: ResponseState()
}