package com.example.test4.presentation.cat_pop_favour

import com.example.test4.domain.category.Category

data class CatPopScreenState (
    val category: List<Category> = emptyList(),
    var categoryVisState: Boolean = false,
    var selectedCategory: Category? = null,
    val label: String = ""
)