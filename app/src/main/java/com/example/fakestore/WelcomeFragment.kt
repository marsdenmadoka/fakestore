package com.example.fakestore

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

//private  val args:WelcomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    }
}