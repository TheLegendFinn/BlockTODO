package com.example.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * TODO: Write Docs
 */
@Database(entities = [Item::class, Block::class], version = 4, exportSchema = false)
abstract class BlockDatabase : RoomDatabase() {
    abstract fun itemDAO(): ItemDAO

    companion object {
        @Volatile
        private var INSTANCE: BlockDatabase? = null

        fun getDatabase(context: Context): BlockDatabase {
            val tmp = INSTANCE

            if (tmp != null) {
                return tmp
            }

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BlockDatabase::class.java,
                    "block_database"
                ).build()
                return INSTANCE as BlockDatabase
            }
        }

    }
}