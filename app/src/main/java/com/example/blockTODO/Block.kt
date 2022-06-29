package com.example.blockTODO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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