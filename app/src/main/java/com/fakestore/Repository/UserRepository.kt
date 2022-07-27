package com.fakestore.Repository

import com.fakestore.Network.Response.UserResponse
import com.fakestore.Network.api.SafeApiCall
import com.fakestore.Network.api.data.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) : SafeApiCall {

    suspend fun getUser() = safeApiCall {
      api.getUser()
    }


//    private suspend fun getAllUser(name: String): UserResponse? {
//        val response = getUser()
//        return response.data?.find { it.username == name }
//       // find { it.username == name}
//    }
}
