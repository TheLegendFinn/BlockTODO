package com.example.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppinglist.Block
import com.example.shoppinglist.Item

/**
 * TODO: Write Docs
 */

@Dao
interface ItemDAO {

    //Get all Items of a specific Block
    @Query("SELECT * from item where blockId = (:block)")
    fun getByBlock(block: Int): LiveData<List<Item>>

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

    @Query("DELETE FROM item WHERE blockId = (:block)")
    suspend fun deleteByBlock(block: Int)

    @Query("DELETE FROM block WHERE id = (:block)")
    suspend fun deleteBlock(block: Int)
}