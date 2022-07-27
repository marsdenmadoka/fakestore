package com.fakestore.Network.api

import android.content.Context
import com.fakestore.Network.Response.LoginResponse
import com.fakestore.datastore.PreferenceDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    context:Context
) :Authenticator {
    private val appContext = context.applicationContext
    private val userPreferences = PreferenceDataStore(appContext)

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            userPreferences.accessToken.first()
            response.request.newBuilder()
                .header("Authorization", "Bearer ${userPreferences.accessToken}")
                .build()
        }
    }
}