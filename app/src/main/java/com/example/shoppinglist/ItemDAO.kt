package com.example.shoppinglist

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
    fun getByBlock(block: Int): List<Item>

    //TODO: Query to get all Items as List<List<Item>>?

    //Get all Blocks
    @Query("SELECT * from block")
    fun getBlocks(): List<Block>

    @Insert
    fun insertItem(item : Item)

    @Insert
    fun insertBlock(block: Block)

    @Delete
    fun deleteItem(item : Item)

    @Delete
    fun deleteBlock(block: Block)
}