package com.example.test4.domain.shoes

import com.example.test4.domain.category.Category
import kotlinx.serialization.Serializable


data class Shoes(
    val id: Long,
    val name: String,
    val cost: Double,
    val category: List<Category> = emptyList(),
    val description: String,
    val image: String,
    val count: Int = 0,
    var inBucket: Boolean = false,
    var isFavourite: Boolean = false
)


