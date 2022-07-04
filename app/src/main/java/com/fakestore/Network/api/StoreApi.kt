package com.fakestore.Network.api

import com.fakestore.Room.ProductEntity
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

/**retrofit
//will inject this to our AppModule class **/
interface StoreApi {

    companion object{
        const val BASE_URL="https://fakestoreapi.com/"
    }
    @GET("products")    //random_productst?size=20
    suspend fun getProducts():List<ProductEntity> /** wil call this in the repository **/


}


// @POST("") //posting our cart items //getting them back and deleting
//suspend fun sendToCartToApi
//@GET("")
//@DELETE