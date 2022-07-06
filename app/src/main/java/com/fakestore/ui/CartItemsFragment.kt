package com.fakestore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakestore.R
import com.fakestore.ViewModel.ProductViewModel
import com.fakestore.databinding.CartItemsBinding
import com.fakestore.ui.adapter.CartAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartItemsFragment : Fragment(R.layout.cart_items) {
    private val viewModel: ProductViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = CartItemsBinding.bind(view)
        val cartAdapter = CartAdapter()


        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.getcart.collect {
                val cart = it ?: return@collect
                binding.apply {
                    cartRecycleView.apply {
                        adapter = cartAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                        setHasFixedSize(true)
                    }
                }
                cartAdapter.submitList(cart)
                // cartviewNoCart.isvisible=cart.isEmpty()
            }
        }

//            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//
//            }
//

    }

    // }

}
