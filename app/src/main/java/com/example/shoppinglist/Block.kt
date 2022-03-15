package com.example.shoppinglist

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database Table for Blocks
 * @param id Database ID (Primary Key)
 * @param name Title of the Block
 * @param color Color of the Block
 */

@Entity
data class Block(
    @ColumnInfo(name = "name")
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "color")
    val color: String = "#" //TODO: String? Int? Color?
) {
    /**
     * Constructor to create Block and add to Database
     * @param context Context
     * @param name Name of the Block
     */
    constructor(context: Context, name: String) : this(name) {
        Thread(Runnable { DatabaseHandler.insertBlock(context, this) }).start()
    }
}