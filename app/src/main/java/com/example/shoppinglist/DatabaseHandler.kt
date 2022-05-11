package com.example.shoppinglist

import android.content.Context
import androidx.lifecycle.LiveData
import java.lang.RuntimeException

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
    suspend fun getItemsByBlock(context: Context, block: Int): List<Item> {
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
}