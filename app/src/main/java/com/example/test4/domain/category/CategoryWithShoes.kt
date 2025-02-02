package com.example.test4.domain.category

import com.example.test4.domain.shoes.Shoes

data class CategoryWithShoes (
    val cat_id: Long,
    val shoes: List<Shoes>
)