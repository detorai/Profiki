package com.example.test4.data.local_data_source.shoes

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class LocalShoes(
    @PrimaryKey(autoGenerate = false) val id: Long,
    @ColumnInfo(name = "shoes_name")val name: String,
    @ColumnInfo(name = "shoes_cost")val cost: Double,
    @ColumnInfo(name = "shoes_count")val count: Int = 0,
    @ColumnInfo(name = "shoes_description")val description: String,
    @ColumnInfo(name = "shoes_image")val image: String,
    @ColumnInfo(name = "shoes_isBucket")var inBucket: Boolean = false,
    @ColumnInfo(name = "shoes_isFavourite")var isFavourite: Boolean = false
)

@Dao
interface ShoesDao {
    @Query("SELECT * FROM LocalShoes")
    fun getAll(): Flow<List<LocalShoes>>

    @Query("SELECT * FROM LocalShoes WHERE shoes_isFavourite = true")
    fun favouriteAll(): Flow<List<LocalShoes>>

    @Query("SELECT * FROM LocalShoes WHERE shoes_isBucket = true")
    fun bucketAll(): Flow<List<LocalShoes>>

    @Query("UPDATE LocalShoes SET shoes_isFavourite = :isFavourite WHERE id = :id")
    fun inFavourite(isFavourite: Boolean, id: Long)

    @Insert
    fun insertAll(shoes: LocalShoes)

    @Query("DELETE FROM LocalShoes")
    fun deleteAll()

    @Query("UPDATE LocalShoes SET shoes_count = shoes_count+1,  shoes_isBucket = :inBucket WHERE id = :id")
    fun inBucket(inBucket: Boolean, id: Long)
}