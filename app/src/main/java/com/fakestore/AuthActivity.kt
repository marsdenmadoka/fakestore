package com.fakestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fakestore.R
import dagger.hilt.android.AndroidEntryPoint

//will add our auth nav graph and host fragment
@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

    }


}