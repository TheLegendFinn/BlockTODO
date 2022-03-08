package com.example.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
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