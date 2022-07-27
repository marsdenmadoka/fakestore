package com.fakestore.ui.Auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fakestore.R
import com.fakestore.ViewModel.AuthViewModel.AuthViewModel
import com.fakestore.databinding.FragmentSignupBinding
import com.fakestore.ui.Home.HomeActivity
import com.fakestore.util.Resource
import com.fakestore.util.enable
import com.fakestore.util.startNewActivity
import kotlinx.coroutines.launch

class SignupFragment : Fragment(R.layout.fragment_signup) {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupBinding.bind(view)


        viewModel.signupResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        //viewModel.saveAccessTokens(it.data?.token!!)
                        requireActivity().startNewActivity(HomeActivity::class.java)//from our ext func
                    }
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "SignUpFailure", Toast.LENGTH_SHORT).show()
                }
            }


        })


        /**when enabling and disabling our button when we write text in our text field*/
        binding.SignUpEmail.addTextChangedListener {
            val username = binding.SignUpUsername.text.toString().trim()
            val password = binding.SignUpPassword.text.toString().trim()

            binding.SignUpButton.enable(username.isNotEmpty()
                    && password.isNotEmpty()
                    && it.toString().isNotEmpty())//using the extension function enable
        }

        binding.SignUpButton.setOnClickListener {
            signUp()
        }

        binding.LoginText.setOnClickListener {
            val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }


    private fun signUp() {
        val email = binding.SignUpEmail.text.toString().trim()
        val password = binding.SignUpPassword.text.toString().trim()
        val username = binding.SignUpUsername.text.toString().trim()
        viewModel.signUpUser(email, username, password)
    }
}