package com.fakestore.util

import okhttp3.ResponseBody

//a generic class that contains data and status about loading this data
sealed class Resource<T>(
    val data: T? = null,
    val error: ResponseBody? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error(
        //throwable: ResponseBody, data: T? = nu        ll,
        val isNetworkError: Boolean,
//        val errorCode: Int?,
//        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
        //(data, throwable)


}