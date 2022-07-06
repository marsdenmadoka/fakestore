package com.fakestore.Room.Dao

import androidx.room.*
import com.fakestore.Room.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    //getting all cartItems
    @Query("SELECT * FROM cartItems")
    fun getAllCartItems() : Flow<List<CartEntity>>

    //Inserting cart items
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItems(product: CartEntity)

    //deleting cart item
    @Delete
    suspend fun delete(product: CartEntity)
}