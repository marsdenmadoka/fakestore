package com.fakestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.fakestore.datastore.PreferenceDataStore
import com.fakestore.ui.Auth.AuthActivity
import com.fakestore.ui.Home.HomeActivity
import com.fakestore.util.startNewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userPreferences = PreferenceDataStore(this)

        userPreferences.accessToken.asLiveData().observe(
            this,
            Observer { //get auth token from preference Mananger as a livedata and observer it
                val activity =
                    if (it == null) AuthActivity::class.java else HomeActivity::class.java//check if the acess token is laready saved i.e if the used is already logged in
                startNewActivity(activity)
            })

    }
}