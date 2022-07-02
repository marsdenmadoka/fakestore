package com.fakestore.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakestore.R
import com.fakestore.Room.ProductEntity
import com.fakestore.ViewModel.ProductViewModel
import com.fakestore.databinding.FragmentHomeBinding
import com.fakestore.ui.adapter.ProductAdapter
import com.fakestore.util.Resource
import com.fakestore.util.handleApiError
import com.fakestore.util.onQueryTextChange
import com.fakestore.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) ,ProductAdapter.OnItemClickListener{
    private val viewModel: ProductViewModel by viewModels()


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
        viewModel.products.observe(viewLifecycleOwner, Observer {
            binding.apply {


                progressBar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                when (it) {
                    is Resource.Success -> {
                        home()
                        productAdapter.submitList(it.data)
                        textViewError.isVisible = false
                    }
                    is Resource.Error -> {
                        textViewError.isVisible = true
                        textViewError.text = "No internet connection"
                        handleApiError(it) { home() }
                    }
                }
            }
        })


        val searchItem = view.findViewById<SearchView>(R.id.home_search_view)
        val searchView = searchItem as SearchView
        searchView.onQueryTextChange {
            viewModel.searchQuery.value = it
        }
//        binding.apply {
//       val search= homeSearchView
//               val searchView=search.onActionViewExpanded() as SearchView
//
//            searchView
//            .onQueryTextChange {
//                //update search query
//                viewModel.searchQuery.value=it
//            }
//        }
    }

    override fun onItemClick(product: ProductEntity) {
        //viewModel.onProductSelected(product)
    }


}















//            { result ->
//                binding.apply {
//                    //progressBar.isVisible =
//                      //  result is Resource.Loading && result.data.isNullOrEmpty()//sow progress bar when resource doesn't contain any data
//                    // textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
//                    //textViewError.text = result.error?.localizedMessage
//
//                    //just a snackbar example
//                    when (result) {
//                        is Resource.Success->{
//                            productAdapter.submitList(result.data)
//                        }
//                        is Resource.Loading->{
//
//                        }
//                        is Resource.Error -> {
//                            textViewError.isVisible=true
//                            textViewError.text = "No internet connection"
//
//                            handleApiError(result) { home() }
//                        }
//
//                    }

//            }
//      }

