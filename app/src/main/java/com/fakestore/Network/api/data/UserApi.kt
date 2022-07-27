package com.fakestore.Network.api.data

import com.fakestore.Network.Response.UserResponse
import retrofit2.http.GET


interface UserApi { //inject to retrofit

    @GET("users/")
    suspend fun getUser(): List<UserResponse> /**not connected to any entity we don,t need to save the user in the db **/

}

