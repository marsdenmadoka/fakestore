package com.fakestore.ui

import android.content.ClipData
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.fakestore.R
import com.fakestore.ViewModel.ProductItemViewModel
import com.fakestore.databinding.ProductItemBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.product_item.*

@AndroidEntryPoint
class ProductItemFragment : Fragment(R.layout.product_item) {

    private val viewModel: ProductItemViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ProductItemBinding.bind(view)


        binding.apply {
//            Glide.with(view)
//                .load(viewModel.productItemImage)
//                .into(product_item_image)

            product_item_title.setText(viewModel.productItemName)
            product_item_category.setText(viewModel.productItemCategory)
        }

    }

}


//        val button = view.findViewById<Button>(R.id.button_confirm)
//        button?.setOnClickListener {
//        val username = view.findViewById<EditText>(R.id.edit_text_username).text.toString()
//          val password = view.findViewById<EditText>(R.id.edit_text_pass).text.toString()
//
//            //this method is generated from the idea of the action/arguments we created in navgraph
//            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username, password)
//            findNavController().navigate(action)
//        }