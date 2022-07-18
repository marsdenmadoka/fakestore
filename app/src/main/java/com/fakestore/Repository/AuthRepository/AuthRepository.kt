package com.fakestore.Repository.AuthRepository

import com.fakestore.Network.api.AuthApi
import com.fakestore.Network.api.SafeApiCall
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
   // private val safeApiCall: SafeApiCall
): SafeApiCall {


    suspend fun login(
        username: String,
        password: String
    ) =safeApiCall {
        api.login(username, password)
    }

}
