package com.fakestore.di

import android.app.Application
import androidx.room.Room
import com.fakestore.Network.api.StoreApi
import com.fakestore.Room.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //injecting Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(StoreApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideStoreApi(retrofit: Retrofit) : StoreApi =
        retrofit.create(StoreApi::class.java)


    //injecting our database
    @Provides
    @Singleton
    fun provideDatabase(app:Application) : ProductDatabase =
        Room.databaseBuilder(app,ProductDatabase::class.java,"Product_Database")
            .build()





}