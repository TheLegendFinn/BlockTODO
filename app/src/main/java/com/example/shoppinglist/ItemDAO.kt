package com.example.shoppinglist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDAO {

    @Query("SELECT * from item where block = (:block)")
    fun getByBlock(block: String): List<Item>

    @Query("SELECT block from item")
    fun getBlocks(): List<String>

    @Insert
    fun insert(item : Item)

    @Delete
    fun delete()
}