package com.fakestore.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakestore.Repository.ProductRepository
import com.fakestore.Room.CartEntity
import com.fakestore.Room.ProductEntity
import com.fakestore.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository,
) : ViewModel() {

    val getCart =
        repository.getCartItems() // To optimize and share a flow when multiple consumers collect at the same time, use the shareIn operator.
            .stateIn(viewModelScope, SharingStarted.Lazily, null)


    /**delete cart item*/
    fun deletecartItem(cartItem: CartEntity) {
        viewModelScope.launch {
            repository.deleteCartItem(cartItem)
        }
    }


    /**adding item to cart */
    fun insertCartItem(cartItem: CartEntity) = viewModelScope.launch {
        repository.addToCart(cartItem)
    }

// val getTotal = getCart.value?.sumOf { it.price!! }

    val getTotal = repository.getCartItems().map { it.sumOf { it.price!! } }



    /**
    fun getCartItemsPriceTotal(): Double {
        var totalPrice = 0.0
        getCart.value?.forEach { (itemId, price) ->
            totalPrice += price * (getCart.value!!.find { it.id ==itemId }?:1)
        }
        return totalPrice
    }


    fun getCartItemsCount(): Int {
        var totalCount = 0
       getCart.value?.forEach {
           totalCount += it.id
       }
        return totalCount
    }*/

}




