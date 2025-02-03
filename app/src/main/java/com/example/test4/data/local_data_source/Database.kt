package com.example.test4.data.local_data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test4.data.local_data_source.shoes.LocalShoes
import com.example.test4.data.local_data_source.shoes.ShoesDao

@Database(entities = [LocalShoes::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun shoes(): ShoesDao
}