package com.example.test4.domain.sign_in

import com.example.test4.data.remote_data_source.sign_in.SignInRepository
import com.example.test4.domain.common.ResponseState
import com.example.test4.domain.sign_in.request.AuthRequest
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.flow

class SignInUseCase {
    private val repository = SignInRepository()

    suspend fun auth(authRequest: AuthRequest) =
        flow<ResponseState>{
            return@flow try{
                val result = repository.auth(authRequest)
                result.sessionStatus.collect{status ->
                    when (status) {
                        is SessionStatus.Initializing -> {
                            emit(ResponseState.Loading())
                        }
                        is SessionStatus.RefreshFailure -> {
                            emit(ResponseState.Error(error = "RefreshFailure"))
                        }
                        is SessionStatus.Authenticated -> {
                            emit(ResponseState.Success(true))
                        }
                        is SessionStatus.NotAuthenticated -> {
                            emit(ResponseState.Error(error = "Not Auth"))
                        }
                    }
                }
            } catch (e: Exception) {
                emit(ResponseState.NetworkError(e.message.toString()))
            }
        }
}