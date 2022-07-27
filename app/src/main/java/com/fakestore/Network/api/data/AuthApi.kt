package com.fakestore.Network.api.data

import com.fakestore.Network.Response.LoginResponse
import com.fakestore.Network.Response.UserResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(/**not connected to any entity we don,t need to save our login in the local db/will store our token jetpack in datastore **/
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse


    @FormUrlEncoded
    @POST("users")
    suspend fun signUp(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String
    ): UserResponse


}