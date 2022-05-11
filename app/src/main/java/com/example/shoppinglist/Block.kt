package com.example.shoppinglist

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Database Table for Blocks
 * @param name Title of the Block
 * @param id Database ID (Primary Key)
 */

@Entity
data class Block(
    @ColumnInfo(name = "name")
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {

}