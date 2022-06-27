package com.fakestore.Room

import androidx.room.Database
import androidx.room.RoomDatabase
//will inject it to hilt
@Database(entities = [ProductEntity::class], version = 1) //we Must declare all the tables we have (entities = [ProductEntity::class,SingleProduct::class],
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao():ProductDao

}