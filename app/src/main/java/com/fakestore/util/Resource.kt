package com.fakestore.util

import okhttp3.ResponseBody

//a generic class that contains data and status about loading this data
sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) :

        Resource<T>(data, throwable)


}


//sealed class Resource<out T> {
//    data class Success<out T>(val value: T) : Resource<T>()
//    data class Error(
//        val isNetworkError: Boolean,
//        val errorCode: Int?,
//        val errorBody: ResponseBody?
//    ) : Resource<Nothing>()
//    object Loading : Resource<Nothing>()
//}