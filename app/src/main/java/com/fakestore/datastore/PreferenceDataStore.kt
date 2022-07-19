package com.fakestore.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store") //datastore instance
class PreferenceDataStore @Inject constructor(@ApplicationContext context: Context)

{
    private val appContext = context.applicationContext

   companion object { //we want to save a value with a respective key
       private val ACCESS_TOKEN = stringPreferencesKey("key_access_token")
   }

    /**saving the auth token*/
   suspend fun saveAccessToken(accessToken:String){
       appContext.dataStore.edit { preferences-> /**this preferences will help us save our auth token//works with key value pairs*/
           preferences[ACCESS_TOKEN] = accessToken//storing our auth token
       }
   }

    /**getting back the auth token using flow because datastore works with  koline couritines and flows*/
    val accessToken: Flow<String?> //so to retrieve the value we need to use flow  in our case of type string
        get() = appContext.dataStore.data.map { preferences -> //getter for our flow to get the token inside the map
            preferences[ACCESS_TOKEN] //with the help of this preferences we can get the token //incase no value is saved we will get a null string
        }

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}









