package com.fakestore.Network.api.data

import com.fakestore.Room.ProductEntity
import retrofit2.http.GET


interface StoreApi {
    /**   companion object{  const val BASE_URL="https://fakestoreapi.com/" } */

    @GET("products")    //random_productst?size=20
    suspend fun getProducts():List<ProductEntity>

}

