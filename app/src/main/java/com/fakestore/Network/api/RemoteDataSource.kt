package com.fakestore.Network.api

import android.content.Context
import com.fakestore.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    companion object {
        private const val BASE_URL = "https://fakestoreapi.com/"
    }
    fun <Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        val authenticator = TokenAuthenticator(context)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient(authenticator))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
    private fun getRetrofitClient(authenticator: TokenAuthenticator?=null): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor { chain -> /**passing authToken to all APIs**/
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build())
              }.also { client->
                authenticator?.let { client.authenticator(it)
                }
                if(BuildConfig.DEBUG){
                    val logging=HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }



}


/**   return Retrofit.Builder()
.baseUrl(BASE_URL)
.client(
OkHttpClient.Builder().also { client ->
if (BuildConfig.DEBUG) {
val logging = HttpLoggingInterceptor()
logging.setLevel(HttpLoggingInterceptor.Level.BODY)
client.addInterceptor(logging)
}
}.build()
)
.addConverterFactory(GsonConverterFactory.create())
.build()
.create(api) */


