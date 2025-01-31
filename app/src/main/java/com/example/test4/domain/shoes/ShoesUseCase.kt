package com.example.test4.domain.shoes

import com.example.test4.data.remote_data_source.shoes.ShoesRepository
import com.example.test4.domain.common.ResponseState
import kotlinx.coroutines.flow.flow

class ShoesUseCase {

    val shoesRepository = ShoesRepository()

    suspend fun getAllShoes() = flow<ResponseState> {
        return@flow try{
            emit (ResponseState.Loading())
            val result = shoesRepository.getShoes().map {
                Shoes(
                    id = it.Shoes.id,
                    name = it.Shoes.shoes_name,
                    cost = it.Shoes.shoes_cost,
                    description = it.Shoes.shoes_description,
                    image = it.Shoes.shoes_url
                )
            }
            emit(ResponseState.Success(data = result))
        } catch (e: Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}