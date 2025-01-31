package com.example.test4.domain.category

import com.example.test4.data.remote_data_source.category.CategoryRepository
import com.example.test4.domain.common.ResponseState
import kotlinx.coroutines.flow.flow

class CategoryUseCase {

    val categoryRepository = CategoryRepository()

    suspend fun getAllCategory() = flow<ResponseState> {
        return@flow try{
            emit(ResponseState.Loading())
            val result = categoryRepository.getAllCategory().map {
                Category(
                    id = it.id,
                    name = it.category_name
                )
            }
            emit(ResponseState.Success(data = result))
        } catch (e:Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}