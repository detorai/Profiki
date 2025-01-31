package com.example.test4.data.remote_data_source.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto (
    val id: Long,
    val category_name: String
)