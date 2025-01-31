package com.example.test4.data.remote_data_source.category

import com.example.test4.data.remote_data_source.SupabaseClient
import io.github.jan.supabase.postgrest.from

class CategoryRepository {
    suspend fun getAllCategory() = SupabaseClient.client.from("Category").select().decodeList<CategoryDto>()
}