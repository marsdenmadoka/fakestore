package com.fakestore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fakestore.R

class LoginFragment : Fragment(R.layout.fragment_login){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val button = view.findViewById<Button>(R.id.button_confirm)
//        button?.setOnClickListener {
//        val username = view.findViewById<EditText>(R.id.edit_text_username).text.toString()
//          val password = view.findViewById<EditText>(R.id.edit_text_pass).text.toString()
//
//            //this method is generated from the idea of the action/arguments we created in navgraph
//            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username, password)
//            findNavController().navigate(action)
//        }
    }

}