package com.example.blockTODO.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.blockTODO.Block
import com.example.blockTODO.Item

/**
 * Implementation of the RoomDatabase
 * Consists of tables for Items and Blocks
 */
@Database(entities = [Item::class, Block::class], version = 6)
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
                ).fallbackToDestructiveMigration().build()
                return INSTANCE as BlockDatabase
            }
        }

    }
}