package com.fakestore.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {

    @Query("SELECT * FROM productstable")//returns all the product in our database table||not a suspending function
    fun getAllProducts() : Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products:List<ProductEntity>)//inserting list of products we get from rest Api to our database

     @Query("DELETE FROM productstable")
    suspend fun deleteAllProducts() //when we fetch new list of products from api we delete the existing one in our local db and replace them with new ones


}