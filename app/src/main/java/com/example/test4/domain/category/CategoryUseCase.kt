package com.example.test4.domain.category

import com.example.test4.data.remote_data_source.category.CategoryRepository
import com.example.test4.domain.common.ResponseState
import com.example.test4.domain.shoes.Shoes
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
    suspend fun getCategoryById(id: Long) = flow<ResponseState> {
        return@flow try {
            emit(ResponseState.Loading())
            val result = categoryRepository.getCategoryById(id).map {
                Shoes(
                    id = it.Shoes.id,
                    name = it.Shoes.shoes_name,
                    cost = it.Shoes.shoes_cost,
                    description = it.Shoes.shoes_description,
                    image = it.Shoes.shoes_url,
                )
            }
            val category = CategoryWithShoes(
                cat_id = id,
                shoes = result
            )
            emit(ResponseState.Success(data = category))
        } catch (e:Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}