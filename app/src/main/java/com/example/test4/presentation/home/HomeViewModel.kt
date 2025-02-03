package com.example.test4.presentation.home

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.test4.domain.category.Category
import com.example.test4.domain.category.CategoryUseCase
import com.example.test4.domain.common.ResponseState
import com.example.test4.domain.sales.Sales
import com.example.test4.domain.sales.SalesUseCase
import com.example.test4.domain.shoes.Shoes
import com.example.test4.domain.shoes.ShoesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.internal.toImmutableList

class HomeViewModel(private val context: Context): ScreenModel {
    val shoesUseCase = ShoesUseCase(context)
    val categoryUseCase = CategoryUseCase()
    val homeState = MutableStateFlow(HomeScreenState())
    val shoesList = mutableStateListOf<Shoes>()
    val salesUseCase = SalesUseCase()


    init {
        getAllShoes()
        getAllCategory()
        getSales()
    }

    fun inFavourite(index: Int, state: Boolean){
        screenModelScope.launch(Dispatchers.IO) {
            shoesList.set(index, shoesList[index].copy(isFavourite = state))
            shoesUseCase.inFavourite(shoesList[index])
        }
    }
    fun inBucket(index: Int, state: Boolean){
        screenModelScope.launch(Dispatchers.IO) {
            shoesList.set(index, shoesList[index].copy(inBucket = state))
            shoesUseCase.inBucket(shoesList[index])
        }
    }

    fun getSales(){
        screenModelScope.launch {
            val result = salesUseCase.getSales()
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        homeState.update {
                            it.copy(sales = response.data as List<Sales>)
                        }
                    }
                    is ResponseState.Loading -> {}
                    is ResponseState.NetworkError -> {}
                }
            }
        }
    }

    private fun getAllCategory() {
        screenModelScope.launch {
            val result = categoryUseCase.getAllCategory()
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        homeState.update {
                            it.copy(category = response.data as List<Category>)
                        }
                    }
                    is ResponseState.Loading -> {}
                    is ResponseState.NetworkError -> {}
                }
            }
        }
    }
    fun getAllShoes() {
        screenModelScope.launch {
            val result = shoesUseCase.getAllShoes()
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        shoesList.clear()
                        shoesList.addAll(response.data as List<Shoes>)
                    }

                    is ResponseState.Loading -> {}
                    is ResponseState.NetworkError -> {}
                }
            }
        }
    }
}