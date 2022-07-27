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
import com.fakestore.databinding.FragmentLoginBinding
import com.fakestore.ui.Home.HomeActivity
import com.fakestore.util.Resource
import com.fakestore.util.enable
import com.fakestore.util.startNewActivity
import com.fakestore.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
//
          binding.loginProgressbar.visible(false)
//        binding.loginButton.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
//           binding.loginProgressbar.visible(it is Resource.Loading)
//            binding.loginProgressbar.visible(false)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAccessTokens(it.data?.token!!)
                        requireActivity().startNewActivity(HomeActivity::class.java)//from our ext func
                    }
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })


        /**when enabling and disabling our button when we write text in our text field*/
        binding.loginPassword.addTextChangedListener {
            val username = binding.loginUsername.text.toString().trim()
            binding.loginButton.enable(username.isNotEmpty() && it.toString().isNotEmpty()
            )//using the extension function enable
        }


        binding.loginButton.setOnClickListener {
            login()
        }

        binding.SignUpText.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }
    }

    private fun login() {
        val password = binding.loginPassword.text.toString().trim()
        val username = binding.loginUsername.text.toString().trim()
        viewModel.loginUser(username, password)
    }

}