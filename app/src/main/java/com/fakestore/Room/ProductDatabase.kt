 package com.fakestore.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fakestore.Room.Dao.CartDao
import com.fakestore.Room.Dao.ProductDao

 //will inject it to hilt
@Database(entities = [ProductEntity::class, CartEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao


}