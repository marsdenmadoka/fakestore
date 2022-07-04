 package com.fakestore.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import javax.inject.Inject
import javax.inject.Provider

 //will inject it to hilt
@Database(entities = [ProductEntity::class,CartEntity::class], version = 1) //we Must declare all the tables we have (entities = [ProductEntity::class,SingleProduct::class],
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao():ProductDao
    abstract fun cartDao():CartDao


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