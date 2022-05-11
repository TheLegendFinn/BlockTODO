package com.example.shoppinglist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * TODO: Write Docs
 */

@Dao
interface ItemDAO {

    //Get all Items of a specific Block
    @Query("SELECT * from item where blockId = (:block)")
    suspend fun getByBlock(block: Int): List<Item>

    //TODO: Query to get all Items as List<List<Item>>?

    //Get all Blocks
    @Query("SELECT * from block")
    fun getBlocks(): LiveData<List<Block>>

    @Insert
    suspend fun insertItem(item: Item)

    @Insert
    suspend fun insertBlock(block: Block)

    @Delete
    suspend fun deleteItem(item: Item)

    @Delete
    suspend fun deleteBlock(block: Block)
}