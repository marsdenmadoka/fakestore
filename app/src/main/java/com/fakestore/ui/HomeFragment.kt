package com.fakestore.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.fakestore.R
import com.fakestore.Room.CartEntity
import com.fakestore.Room.ProductEntity
import com.fakestore.ViewModel.CartViewModel
import com.fakestore.ViewModel.ProductViewModel
import com.fakestore.databinding.FragmentHomeBinding
import com.fakestore.ui.adapter.ProductAdapter
import com.fakestore.util.Resource
import com.fakestore.util.exhaustive
import com.fakestore.util.onQueryTextChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_list_item_layout.*
import kotlinx.android.synthetic.main.product_item.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ProductAdapter.OnItemClickListener {
    private val viewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentHomeBinding.bind(view)
        val productAdapter = ProductAdapter(this)


        viewModel.getUser()
        fun home() {
            binding.apply {
                homerecyclerview.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = productAdapter
                    setHasFixedSize(true)
                }
            }
        }
        viewModel.products.observe(viewLifecycleOwner) { it ->
            home()
            productAdapter.submitList(it.data)
            AllCategories.isChecked = true
            binding.apply {
                when (it) {
                    is Resource.Loading -> {
                        progressBar.isVisible = it.data.isNullOrEmpty()

                    }
                    is Resource.Success -> {

                        text_view_error.isVisible = false
                        progressBar.isVisible = false
                        // AllCategories.isChecked=true
                    }
                    is Resource.Error -> {
                        text_view_error.isVisible = it.error != null && it.data.isNullOrEmpty()
                        text_view_error.text = getString(
                            R.string.could_not_refresh,
                            it.error?.localizedMessage ?: getString(R.string.unknown_error_occurred)
                        )
                        progressBar.isVisible = false
                        // handleApiError(it) { home() }
                    }
                }.exhaustive
            }
            /** not the right to do this fetch categories from view model*/
            electronicsCategory.setOnClickListener { res ->
                //viewModel.getElectronics()
                productAdapter.submitList(it.data?.filter { it.category == "electronics" })
            }
            jewelery.setOnClickListener { res ->
                productAdapter.submitList(it.data?.filter { it.category == "jewelery" })
            }
            menscloth.setOnClickListener { res ->
                productAdapter.submitList(it.data?.filter { it.category == "men's clothing" })
            }
        }


        /**getting user*/
        viewModel.user.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> { //Resource file for errors and success
                    //updateUI(it.data?.find { it.username }!!)
                    //  binding.homeUserName.text = viewModel.getUsername
                    // it.data?.find { it.username == home_userName.text }
                }
                is Resource.Error -> {
                    binding.textViewError.text = it.error?.localizedMessage
                }
            }
        })

        binding.apply {
            val searchView = homeSearchView
            searchView.onQueryTextChange {
                /**update search query*/
                viewModel.searchQuery.value = it
            }
        }

    }

    override fun onItemClick(product: ProductEntity) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductItemFragment(product)
        findNavController().navigate(action)
    }

    override fun onAddToCartClicked(cartItem: ProductEntity) {
       // cartViewModel.insertCartItem(cartItem)
        // using this for now
        val action = HomeFragmentDirections.actionHomeFragmentToProductItemFragment(cartItem)
        findNavController().navigate(action)
    }



}