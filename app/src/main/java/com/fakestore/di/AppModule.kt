package com.fakestore.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.fakestore.Network.api.data.AuthApi
import com.fakestore.Network.api.RemoteDataSource
import com.fakestore.Network.api.data.StoreApi
import com.fakestore.Network.api.data.UserApi
import com.fakestore.Room.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): AuthApi {
        return remoteDataSource.buildApi(AuthApi::class.java, context)
    }

    @Singleton
    @Provides
    fun provideProductApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): StoreApi {
        return remoteDataSource.buildApi(StoreApi::class.java, context)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): UserApi {
        return remoteDataSource.buildApi(UserApi::class.java, context)
    }

    //injecting our database
    @Provides
    @Singleton //create one instance of our database
    fun provideDatabase(app:Application) : ProductDatabase =
        Room.databaseBuilder(app,ProductDatabase::class.java,"product_Database")
            .fallbackToDestructiveMigration()
            .build()

}






//injecting Retrofit
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit =
//        Retrofit.Builder()
//            .baseUrl(StoreApi.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    @Provides
//    @Singleton
//    fun provideStoreApi(retrofit: Retrofit) : StoreApi =
//        retrofit.create(StoreApi::class.java)
