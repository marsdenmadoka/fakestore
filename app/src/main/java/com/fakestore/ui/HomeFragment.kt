package com.fakestore.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.fakestore.R
import com.fakestore.Room.ProductEntity
import com.fakestore.ViewModel.ProductViewModel
import com.fakestore.databinding.FragmentHomeBinding
import com.fakestore.ui.adapter.ProductAdapter
import com.fakestore.util.Resource
import com.fakestore.util.exhaustive
import com.fakestore.util.onQueryTextChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ProductAdapter.OnItemClickListener {
    private val viewModel: ProductViewModel by viewModels()

    //STOP DEALING WITH MINORS ISSUES... YOU MUST DEAL WITH LEARNING MAIN CORE CONCEPTS!!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentHomeBinding.bind(view)
        val productAdapter = ProductAdapter(this)


        fun home() {

            binding.apply {
                homerecyclerview.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = productAdapter
                    setHasFixedSize(true)
                }
            }
        }
        viewModel.products.observe(viewLifecycleOwner) {

            home()
            productAdapter.submitList(it.data)
            AllCategories.isChecked=true
            binding.apply {
                when (it) {
                    is Resource.Loading -> {
                        progressBar.isVisible = it.data.isNullOrEmpty()

                    }
                    is Resource.Success -> {
                        textViewError.isVisible = false
                        progressBar.isVisible = false
                        // AllCategories.isChecked=true
                    }
                    is Resource.Error -> {
                        textViewError.isVisible = it.error != null && it.data.isNullOrEmpty()
                        textViewError.text = getString(
                            R.string.could_not_refresh,
                            it.error?.localizedMessage ?: getString(R.string.unknown_error_occurred)
                        )
                        progressBar.isVisible = false
                        // handleApiError(it) { home() }
                    }
                }.exhaustive

                electronicsCategory.setOnClickListener { res ->
                    //viewModel.getByElectronicsCategory()
                    productAdapter.submitList(it.data?.filter { it.category == "electronics" })
                }
                jewelery.setOnClickListener { res ->
                    //viewModel.getByElectronicsCategory()
                    productAdapter.submitList(it.data?.filter { it.category == "jewelery" })
                }

                menscloth.setOnClickListener { res ->
                    //viewModel.getByElectronicsCategory()
                    productAdapter.submitList(it.data?.filter { it.category == "men's clothing" })
                }

            }
        }

        binding.apply {
            val searchView = homeSearchView
            searchView.onQueryTextChange {
                /**update search query*/
                viewModel.searchQuery.value = it
                //if (it.isNullOrBlank() ){
                //    textViewError.text = "no search results found"
                //  }
            }
        }

    }

    override fun onItemClick(product: ProductEntity) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductItemFragment(product)
        findNavController().navigate(action)
        // viewModel.onSingleProductClicked(product)
    }


}