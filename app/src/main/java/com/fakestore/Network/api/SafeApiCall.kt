package com.fakestore.Network.api

import com.fakestore.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                        Resource.Error(throwable,null)
                }
            }
        }
    }


