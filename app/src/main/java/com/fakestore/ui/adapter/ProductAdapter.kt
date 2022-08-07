package com.fakestore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fakestore.Room.ProductEntity
import com.fakestore.databinding.HomeListItemLayoutBinding

class ProductAdapter(private val listener: OnItemClickListener) :
    ListAdapter<ProductEntity, ProductAdapter.StoreViewHolder>(StoreComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding =
            HomeListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class StoreViewHolder(private val binding: HomeListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val product = getItem(position)
                        listener.onItemClick(product)
                    }
                }

                homeAddToCart.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val cartItem = getItem(position)
                        listener.onAddToCartClicked(cartItem)
                    }
                }
            }
        }
        fun bind(product: ProductEntity) {
            binding.apply {

                Glide.with(itemView)
                    .load(product.image)
                    .into(productImage)

                productCategory.text = product.category
                productTitle.text = product.title
                priceView.text = "$ ${product.price.toString()}"
                //productDescription.text = store.description
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(product: ProductEntity)
        fun onAddToCartClicked(cartItem: ProductEntity)
    }

    class StoreComparator : DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity) =
            oldItem.id == newItem.id //refer to our store dataclass


        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity) =
            oldItem == newItem

    }
}