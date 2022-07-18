package com.fakestore.Network.api

import com.fakestore.Room.ProductEntity
import retrofit2.http.GET

/**retrofit
//will inject this to our AppModule class **/
interface StoreApi {

//    companion object{
//        const val BASE_URL="https://fakestoreapi.com/"
//    }
    @GET("products")    //random_productst?size=20
    suspend fun getProducts():List<ProductEntity> /** wil call this in the repository **/


   // @GET("products/categories")
//    suspend fun getProductsByCategory():List<String>
}


