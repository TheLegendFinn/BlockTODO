package com.example.shoppinglist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Database Table for Items
 * @param id Database ID (Primary Key)
 * @param text Text of the Item
 * @param blockId Id of the Items Block
 */

@Entity(
    //Defines blockId as a ForeignKey
    foreignKeys = [ForeignKey(
        entity = Block::class,
        parentColumns = ["id"],
        childColumns = ["blockId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "blockId", index = true)
    val blockId: Int
) {
}
