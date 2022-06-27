package com.fakestore.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Number,
    val title: String,
    val price: Number,
    val category: String,
    val description: String,
    val image: String
) {

}
