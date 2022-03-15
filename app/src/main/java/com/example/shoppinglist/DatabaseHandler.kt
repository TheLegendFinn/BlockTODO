package com.example.shoppinglist

import android.content.Context
import java.lang.RuntimeException

/**
 * Provides an API to access the Database
 */

object DatabaseHandler {

    /**
     * Checks if the function is called from main-thread
     * @throws RuntimeException
     */
    private fun checkThread() {
        if (Thread.currentThread().name == "main") {
            throw RuntimeException("Cannot be called from main thread!")
        }
    }

    /**
     * Get all Items of a specific Block
     * @param context Context
     * @param block Id of the Block
     * @return  A List of all Items belonging to [block]
     */
    fun getItemsByBlock(context: Context, block: Int): List<Item> {
        checkThread()
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        return dao.getByBlock(block)
    }

    /**
     * Get all Blocks
     * @param context Context
     * @return A List of all Blocks
     */
    fun getBlocks(context: Context): List<Block> {
        checkThread()
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        return dao.getBlocks()
    }

    /**
     * Inserts an Item into the Database
     * @param context Context
     * @param item The Item to add
     */
    fun insertItem(context: Context, item: Item) {
        checkThread()
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        dao.insertItem(item)
    }

    /**
     * Inserts a Block into the Database
     * @param context Context
     * @param block The Block to add
     */
    fun insertBlock(context: Context, block: Block) {
        checkThread()
        val database = BlockDatabase.getDatabase(context)
        val dao = database.itemDAO()
        dao.insertBlock(block)
    }
}