package com.example.test4.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.test4.domain.category.Category
import com.example.test4.domain.category.CategoryUseCase
import com.example.test4.domain.common.ResponseState
import com.example.test4.domain.shoes.Shoes
import com.example.test4.domain.shoes.ShoesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

class HomeViewModel: ScreenModel {
    val shoesUseCase = ShoesUseCase()
    val categoryUseCase = CategoryUseCase()
    val homeState = MutableStateFlow(HomeScreenState())
    val shoesList = mutableStateListOf<Shoes>()

    init {
        getAllShoes()
        getAllCategory()
    }

    fun inFavourite(index: Int, state: Boolean){
        screenModelScope.launch {
            shoesList.set(index, shoesList[index].copy(isFavourite = state))
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