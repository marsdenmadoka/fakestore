package com.fakestore.Network.api

import com.fakestore.Network.Response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("auth/login")
   suspend fun login(
        @Field("username") username:String,
        @Field("password") password:String
    ):LoginResponse


}