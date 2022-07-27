package com.fakestore.ui.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fakestore.R
import com.fakestore.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        SetUpNav()
//
//    }
//
//    private fun SetUpNav() {
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//                as NavHostFragment
//
//        NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.navController)
//
//        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.homeFragment -> setBottomNavVisibility(View.VISIBLE)
////                R.id.ProductItemFragment,
//                R.id.cartItems -> setBottomNavVisibility(View.VISIBLE)
//                else -> setBottomNavVisibility(View.GONE)
//            }
//        }
//
//    }
//
//
//    private fun setBottomNavVisibility(visibility: Int) {
//        binding.bottomNav.visibility = visibility
//
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//
//    }

        /**navHost*/
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        /**bottom navigation*/
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav?.setupWithNavController(navController)


    }

}






/**navHost*/
/** val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
navController = navHostFragment.findNavController()
/**bottom navigation*/
val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
bottomNav?.setupWithNavController(navController)
/**toolbar*/
val toolbar : Toolbar? = findViewById(R.id.toolbar)
setSupportActionBar(toolbar)
setupActionBarWithNavController(navController)*/