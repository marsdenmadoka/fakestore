package com.fakestore.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakestore.Repository.ProductRepository
import com.fakestore.Room.CartEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository,
) : ViewModel() {


    /**we can also decide to use flow and collect it our ui
     * val products = repository.getProducts().stateIn(viewModelScope, SharingStarted.Lazily, null)*/
    val getCart =
        repository.getCartItems() // To optimize and share a flow when multiple consumers collect at the same time, use the shareIn operator.
            .stateIn(viewModelScope, SharingStarted.Lazily, null)


    /**delete cart item*/
    fun deletecartItem(cartItem: CartEntity) {
        viewModelScope.launch {
            repository.deleteCartItem(cartItem)
        }
    }




/** NOT WORKING
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
