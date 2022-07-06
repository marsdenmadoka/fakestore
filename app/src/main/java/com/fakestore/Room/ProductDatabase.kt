 package com.fakestore.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fakestore.Room.Dao.CartDao
import com.fakestore.Room.Dao.ProductDao
import com.fakestore.Room.Entity.CartEntity
import com.fakestore.Room.Entity.ProductEntity

 //will inject it to hilt
@Database(entities = [ProductEntity::class, CartEntity::class], version = 1) //we Must declare all the tables we have (entities = [ProductEntity::class,SingleProduct::class],
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao


//class Callback @Inject constructor(
//    private val database:Provider<ProductDatabase>
//) : RoomDatabase.Callback(){
//    override fun onCreate(db: SupportSQLiteDatabase) {
//        super.onCreate(db)
//
//        database.get()
//    }
//}

}