package com.fakestore.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.fakestore.R
import com.fakestore.ViewModel.ProductItemViewModel
import com.fakestore.databinding.ProductItemBinding
import com.fakestore.util.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cart_items.*
import kotlinx.android.synthetic.main.product_item.*

@AndroidEntryPoint
class ProductItemFragment : Fragment(R.layout.product_item) {

    private val viewModel: ProductItemViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ProductItemBinding.bind(view)


        binding.apply {
            Glide.with(root)
                .load(viewModel.productItemImage)
                .into(product_item_image)

            product_item_title.text = viewModel.productItemName
            product_item_category.text = viewModel.productItemCategory
            product_item_description.text = viewModel.productItemDescription
            product_item_price.text = "$" + viewModel.productItemPrice.toString()



            cart_icon.setOnClickListener {
                try {
                    viewModel.addToCart()
                    // Resource.Success(it)
                    Snackbar.make(requireView(), "successful added to cart!", Snackbar.LENGTH_LONG).show()
                } catch (throwable: Throwable) {
                    Resource.Error(throwable, it)
                }
            }
        }
    }
}