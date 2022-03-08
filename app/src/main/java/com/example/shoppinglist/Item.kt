package com.example.shoppinglist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "block")
    val block: String
) {
}