package com.example.test4.domain.shoes

import android.content.Context
import androidx.room.Room
import com.example.test4.data.local_data_source.Database
import com.example.test4.data.local_data_source.shoes.LocalShoes
import com.example.test4.data.remote_data_source.shoes.ShoesRepository
import com.example.test4.domain.common.ResponseState
import kotlinx.coroutines.flow.flow

class ShoesUseCase(context: Context) {

    val shoesRepository = ShoesRepository()
    val db = Room.databaseBuilder(
        context = context,
        Database::class.java, "shoesDatabase"
    ).build()
    val shoesDb = db.shoes()

    suspend fun getAllShoes() = flow<ResponseState> {
        return@flow try{
            emit (ResponseState.Loading())
            val result = shoesRepository.getShoes().map {
                Shoes(
                    id = it.Shoes.id,
                    name = it.Shoes.shoes_name,
                    cost = it.Shoes.shoes_cost,
                    description = it.Shoes.shoes_description,
                    image = it.Shoes.shoes_url,
                )
            }
            emit(ResponseState.Success(data = result))
        } catch (e: Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }
    suspend fun inBucket(shoes: Shoes) {
            val result = shoesDb.insertAll(
                LocalShoes(
                    id = shoes.id,
                    name = shoes.name,
                    cost = shoes.cost,
                    description = shoes.description,
                    image = shoes.image,
                    inBucket = shoes.inBucket,
                    count = shoes.count
                )
            )
    }
    suspend fun inFavourite(shoes: Shoes) {
        val result = shoesDb.insertAll(
            LocalShoes(
                id = shoes.id,
                name = shoes.name,
                cost = shoes.cost,
                description = shoes.description,
                image = shoes.image,
                isFavourite = shoes.isFavourite
            )
        )
    }

    suspend fun getAllFavouriteLocal() = flow<ResponseState> {
        return@flow try {
            emit(ResponseState.Loading())
            val result = shoesDb.favouriteAll().collect { it ->
                emit(ResponseState.Success(data = it.map {
                    Shoes(
                        id = it.id,
                        name = it.name,
                        cost = it.cost,
                        description = it.description,
                        image = it.image,
                        isFavourite = it.isFavourite
                    )
                }))
            }
        } catch (e:Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }
    suspend fun getAllBucketShoesLocal() = flow<ResponseState> {
        return@flow try {
            emit(ResponseState.Loading())
            val result = shoesDb.bucketAll().collect { it ->
                emit(ResponseState.Success(data = it.map {
                    Shoes(
                        id = it.id,
                        name = it.name,
                        cost = it.cost,
                        description = it.description,
                        image = it.image,
                        isFavourite = it.isFavourite
                    )
                }))
            }
        } catch (e:Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}