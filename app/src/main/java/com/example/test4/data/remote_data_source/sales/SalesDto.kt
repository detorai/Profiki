package com.example.test4.data.remote_data_source.sales

import kotlinx.serialization.Serializable

@Serializable
data class SalesDto (
    val id: Long,
    val sales_name: String,
    val sales_url: String
)