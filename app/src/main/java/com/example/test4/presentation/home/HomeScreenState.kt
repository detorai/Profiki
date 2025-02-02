package com.example.test4.presentation.home

import com.example.test4.domain.category.Category
import com.example.test4.domain.sales.Sales
import com.example.test4.domain.shoes.Shoes

data class HomeScreenState(
    var shoes: MutableList<Shoes> = mutableListOf(),
    var isLoading: Boolean = false,
    val category: List<Category> = emptyList(),
    val sales: List<Sales> = emptyList()
)