package com.fakestore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fakestore.Room.ProductEntity
import com.fakestore.databinding.HomeListItemLayoutBinding

class ProductAdapter :
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

    class StoreViewHolder(private val binding: HomeListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(store: ProductEntity) {
            binding.apply {

                //image with glide code here
                Glide.with(itemView)
                    .load(store.image)
                    .into(productImage)

               // productPrice.text = store.price.toString()
                productPrice.text = store.category
                productTitle.text = store.title
                //productDescription.text = store.description
            }
        }
    }

    class StoreComparator : DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity) =
            oldItem.id == newItem.id //refer to our store dataclass


        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity) =
            oldItem == newItem

    }
}