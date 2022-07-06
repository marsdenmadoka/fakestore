package com.fakestore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fakestore.R

class FavoriteItemsFragment : Fragment(R.layout.favourite_items) {

//private  val args:WelcomeFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}









//      view.findViewById<TextView>(R.id.view_username).text = args.username
//        view.findViewById<TextView>(R.id.text_view_password).text = args.password
//
//        //going back to home activity
//        val btnOk = view.findViewById<Button>(R.id.button_ok)
//        btnOk.setOnClickListener {
//            val action =WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
//            findNavController().navigate(action)
//
//        }
