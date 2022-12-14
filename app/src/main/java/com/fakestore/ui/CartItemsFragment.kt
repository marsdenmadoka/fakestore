package com.fakestore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakestore.R
import com.fakestore.Room.CartEntity
import com.fakestore.ViewModel.CartViewModel
import com.fakestore.databinding.CartItemsBinding
import com.fakestore.ui.adapter.CartAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartItemsFragment : Fragment(R.layout.cart_items), CartAdapter.OnItemClickListener {
    private val viewModel: CartViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = CartItemsBinding.bind(view)
        val cartAdapter = CartAdapter(this) // val cartAdapter = CartAdapter()


        viewLifecycleOwner.lifecycleScope.launch {//collecting a flow 
            viewModel.

            getCart.collect {
                val cart = it ?: return@collect
                binding.apply {
                    cartRecycleView.apply {
                        adapter = cartAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                        setHasFixedSize(true)
                    }
                }
                cartAdapter.submitList(cart)

              //binding.orderTotalTxtView.text = it.sumOf { it.price!! }.toString()
            }


        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getTotal.collect{
                binding.orderTotalTxtView.text = (it ?: return@collect).toString()
            }
        }

    }

    override fun onRemoveFromCartClicked(cartItem: CartEntity) {
        viewModel.deletecartItem(cartItem)
    }

}
