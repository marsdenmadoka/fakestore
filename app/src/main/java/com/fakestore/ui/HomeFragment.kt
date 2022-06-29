package com.fakestore.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakestore.R
import com.fakestore.ViewModel.ProductViewModel
import com.fakestore.databinding.FragmentHomeBinding
import com.fakestore.ui.adapter.ProductAdapter
import com.fakestore.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: ProductViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val productAdapter = ProductAdapter()

        binding.apply {
            homerecyclerview.apply {
                adapter = productAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            viewModel.products.observe(viewLifecycleOwner) { result ->
                productAdapter.submitList(result.data)
                progressBar.isVisible =
                    result is Resource.Loading && result.data.isNullOrEmpty()//sow progress bar when resource doesn't contain any data
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage

            }
        }
    }


}


//        val button = view.findViewById<Button>(R.id.button_login)
//        button?.setOnClickListener {
//
//            //this method is generated from the idea of the action we created in navgraph
//            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
//            findNavController().navigate(action)
//        }
