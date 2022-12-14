package com.fakestore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fakestore.Room.CartEntity
import com.fakestore.Room.ProductEntity
import com.fakestore.databinding.CartRowLayoutBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class CartAdapter (private val listener: CartAdapter.OnItemClickListener): ListAdapter<CartEntity, CartAdapter.CartViewHolder>(CartComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            CartRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }


    inner class CartViewHolder(private val binding: CartRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                deleteProductButton.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val cartItem = getItem(position)
                        listener.onRemoveFromCartClicked(cartItem)
                    }
                }



            }
        }
        fun bind(cart: CartEntity) {
            binding.apply {
                Glide.with(itemView)
                    .load(cart.image)
                    .into(cartProductImage)

                cartNameTextView.text = cart.title
                productPriceTextView.text = cart.price.toString()



            }
        }

    }


    interface OnItemClickListener {
      fun onRemoveFromCartClicked(cartItem: CartEntity)
    }

    class CartComparator : DiffUtil.ItemCallback<CartEntity>() {
        override fun areItemsTheSame(oldItem: CartEntity, newItem: CartEntity) =

            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CartEntity, newItem: CartEntity) =
            oldItem == newItem


    }

}