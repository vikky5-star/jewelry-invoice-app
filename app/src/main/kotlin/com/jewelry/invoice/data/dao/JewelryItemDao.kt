package com.jewelry.invoice.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jewelry.invoice.data.entity.JewelryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface JewelryItemDao {
    @Insert
    suspend fun insert(item: JewelryItem): Long

    @Update
    suspend fun update(item: JewelryItem)

    @Delete
    suspend fun delete(item: JewelryItem)

    @Query("SELECT * FROM jewelry_items WHERE id = :id")
    suspend fun getItemById(id: Int): JewelryItem?

    @Query("SELECT * FROM jewelry_items ORDER BY itemName ASC")
    fun getAllItems(): Flow<List<JewelryItem>>

    @Query("SELECT * FROM jewelry_items WHERE itemName LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%'")
    fun searchItems(query: String): Flow<List<JewelryItem>>

    @Query("SELECT * FROM jewelry_items WHERE category = :category")
    fun getItemsByCategory(category: String): Flow<List<JewelryItem>>
}