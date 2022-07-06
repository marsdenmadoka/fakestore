package com.fakestore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fakestore.Room.CartEntity
import com.fakestore.databinding.CartRowLayoutBinding

class CartAdapter : ListAdapter<CartEntity, CartAdapter.CartViewHolder>(CartComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

     return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null){
            holder.bind(currentItem)
        }

    }


    inner class CartViewHolder(private val binding: CartRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cart: CartEntity) {

            binding.apply {

                Glide.with(itemView)
                    .load(cart.image)
                    .into(cartProductImage)


                cartNameTextView.text = cart.title

            }
        }

    }


    class CartComparator : DiffUtil.ItemCallback<CartEntity>() {
        override fun areItemsTheSame(oldItem: CartEntity, newItem: CartEntity)=

            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CartEntity, newItem: CartEntity) =
           oldItem == newItem


    }

}