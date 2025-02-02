package com.example.test4.presentation.cat_pop_favour

import androidx.compose.runtime.mutableStateListOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.test4.domain.category.Category
import com.example.test4.domain.category.CategoryUseCase
import com.example.test4.domain.category.CategoryWithShoes
import com.example.test4.domain.common.ResponseState
import com.example.test4.domain.shoes.Shoes
import com.example.test4.domain.shoes.ShoesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatPopViewModel(type: ScreenType, category: Category? = null): ScreenModel {
    val categoryUseCase = CategoryUseCase()

    val shoesList = mutableStateListOf<Shoes>()
    val screenState = MutableStateFlow(CatPopScreenState())

    init {
        when (type){
            ScreenType.POPULAR -> {
                screenState.update {
                    it.copy(label = "Популярное")
                }
                getCategoryById(6)
            }
            ScreenType.CATEGORY -> {
                getAllCategory()
            }
            ScreenType.FAVOURITE -> {}
        }
        category?.let { category ->
            selectedCategory(category)
            screenState.update {
                it.copy(
                    label = category.name,
                    selectedCategory = category,
                    categoryVisState = true
                )
            }
        }
    }
    fun selectedCategory(category: Category){
        screenState.update {
            it.copy(
                selectedCategory = category,
                label = category.name,
            )
        }
        getCategoryById(
            category.id
        )
    }



    fun getAllCategory() {
        screenModelScope.launch {
            val result = categoryUseCase.getAllCategory()
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        screenState.update {
                            it.copy(category = response.data as List<Category>)
                        }
                    }
                    is ResponseState.Loading -> {}
                    is ResponseState.NetworkError -> {}
                }
            }
        }
    }
    fun getCategoryById(id: Long){
        screenModelScope.launch {
            val result = categoryUseCase.getCategoryById(id)
            result.collect { response ->
                when (response) {
                    is ResponseState.Error -> {}
                    is ResponseState.Success<*> -> {
                        shoesList.apply {
                            clear()
                            addAll(
                                (response.data as CategoryWithShoes).shoes
                            )
                        }
                    }
                    is ResponseState.Loading -> {}
                    is ResponseState.NetworkError -> {}
                }
            }
        }

    }
}