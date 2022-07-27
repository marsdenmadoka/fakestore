package com.fakestore.Repository.AuthRepository

import com.fakestore.Network.api.data.AuthApi
import com.fakestore.Network.api.SafeApiCall
import com.fakestore.datastore.PreferenceDataStore
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: PreferenceDataStore
) : SafeApiCall {


    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(username, password)
    }

    suspend fun saveAccessTokens(accessToken: String) {
        preferences.saveAccessToken(accessToken)
    }

    suspend fun SignUp(
        email: String,
        username:String,
        password:String,
    ) = safeApiCall {
        api.signUp(email,username,password)
    }

}
