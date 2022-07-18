package com.fakestore.Room.Dao

import androidx.room.*
import com.fakestore.Room.ProductEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {

    @Query("SELECT * FROM products_table WHERE title LIKE '%' || :searchQuery || '%' ")//returns all the product in our database table||not a suspending function
    fun getAllProducts(searchQuery:String) : Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products:List<ProductEntity>)//inserting list of products we get from rest Api to our database

     @Query("DELETE FROM products_table ")
    suspend fun deleteAllProducts() //when we fetch new list of products from api we delete the existing one in our local db and replace them with new ones

   // @Query("SELECT FROM products")






//    @Update
//    suspend fun updateProducts()
//
//    @Delete
//    suspend fun DeleteProducts()

//    @Query("SELECT * FROM productstable")//returns all the product in our database table||not a suspending function
//    fun getAllProducts() : Flow<List<ProductEntity>>
}