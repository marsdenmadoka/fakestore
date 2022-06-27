package com.fakestore.Network.api

import com.fakestore.Room.Store
import retrofit2.http.GET

//retrofit
interface StoreApi {

    companion object{
        const val BASE_URL="https://fakestoreapi.com"
    }
    @GET("/products") //"/products?size-20"
    suspend fun getProducts():List<Store>


}