package com.example.shoppinglist.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.shoppinglist.Block
import com.example.shoppinglist.Item

/**
 * Provides an API to access the Database
 */
object DatabaseHandler {
    /**
     * Get all Items of a specific Block
     * @param context Context
     * @param block Id of the Block
     * @return  A List of all Items belonging to [block]
     */
    fun getItemsByBlock(context: Context, block: Int): LiveData<List<Item>> {
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        return dao.getByBlock(block)
    }

    /**
     * Get all Blocks
     * @param context Context
     * @return A LiveData-List of all Blocks
     */
    fun getBlocks(context: Context): LiveData<List<Block>> {
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        return dao.getBlocks()
    }

    /**
     * Inserts an Item into the Database
     * @param context Context
     * @param item The Item to add
     */
    suspend fun insertItem(context: Context, item: Item) {
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        dao.insertItem(item)
    }

    /**
     * Inserts a Block into the Database
     * @param context Context
     * @param block The Block to add
     */
    suspend fun insertBlock(context: Context, block: Block) {
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        dao.insertBlock(block)
    }

    /**
     * Deletes all Items of a given Block
     * @param context Context
     * @param block Block to delete all Items from
     */
    suspend fun deleteByBlock(context: Context, block: Int) {
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        dao.deleteByBlock(block)
    }

    /**
     * Deletes a Block from the Database
     * @param context
     * @param block Id of the block too delete
     */
    suspend fun deleteBlock(context: Context, block: Int) {
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        dao.deleteBlock(block)
    }

    /**
     * Deletes an Item from the Database
     * @param context
     * @param item Id of the Item to delete
     */
    suspend fun deleteItem(context: Context, item: Int) {
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        dao.deleteItem(item)
    }
}