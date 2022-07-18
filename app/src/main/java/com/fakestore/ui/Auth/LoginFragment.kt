package com.fakestore.ui.Auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.fakestore.R
import com.fakestore.ViewModel.AuthViewModel.AuthViewModel
import com.fakestore.databinding.FragmentLoginBinding
import com.fakestore.util.Resource
import com.fakestore.util.enable
import com.fakestore.util.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

//        binding.loginProgressbar.visible(false)
//        binding.loginButton.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
          //  binding.loginProgressbar.visible(it is Resource.Loading)
            when(it) {
                is Resource.Success ->{
                   Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_LONG).show()
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(),"Login Failure",Toast.LENGTH_SHORT).show()
                }
            }
        })



        //when enabling and disabling our button when we write text in our text field
//        binding.loginPassword.addTextChangedListener {
//            val username = binding.loginUsername.text.toString().trim()
//            binding.loginButton.enable(
//                username.isNotEmpty() && it.toString().isNotEmpty()
//            )//using the extension function enable
//        }


        binding.loginButton.setOnClickListener {
            login()
        }

       binding.SignUpText.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }
    }

    private fun login() {
        val username = binding.loginUsername.text.toString().trim()
        val password = binding.loginPassword.text.toString().trim()

        viewModel.loginUser(username, password)
    }

}
/**
java.lang.RuntimeException: Unable to start activity ComponentInfo{com.fakestore/com.fakestore.
AuthActivity}: android.view.InflateException: Binary XML file line #9: Binary XML file line #9:
Error inflating class androidx.fragment.app.FragmentContainerView
at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2724) **/