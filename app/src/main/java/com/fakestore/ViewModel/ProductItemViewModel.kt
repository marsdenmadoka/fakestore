package com.fakestore.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakestore.Repository.ProductRepository
import com.fakestore.Room.CartEntity
import com.fakestore.Room.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//displaying a single product
@HiltViewModel
class ProductItemViewModel @Inject constructor(
    /**we can use the SavedStateHandle to store little pieces of information to restore/recreating our ui
     * but also store the navigation arguments we sent over to this screen **/
    private val state:SavedStateHandle,
    private val repository: ProductRepository,
): ViewModel() {

    val product = state.get<ProductEntity>("ProductItem") //key of te argument MUST be the same with that from the navgraph

    var productItemName = state.get<String>("productItemName") ?:product?.title ?:""
    set(value) { //storing it in a save instance state
        field=value
        state.set("productItemName",value)
    }


    var productItemCategory = state.get<String>("productItemCategory") ?:product?.category ?:""
        set(value) {
            field=value
            state.set("productItemCategory",value)
        }


    var productItemImage = state.get<String>("productItemImage") ?:product?.image?:""
        set(value) {
            field=value
            state.set("productItemImage",value)
        }


    var productItemPrice = state.get<String>("productItemPrice") ?:product?.price?:""
        set(value) {
            field=value
            state.set("productItemPrice",value)
        }

    var productItemDescription = state.get<String>("productItemDescription") ?:product?.description?:""
        set(value) {
            field=value
            state.set("productItemDescription",value)
        }

    fun addToCart(){/**adding our cartItems to our local db*/
        val newCart= CartEntity(title=productItemName,category=productItemCategory,image=productItemImage,price= productItemPrice as Double)
        /**check if the cart exist*/
        viewModelScope.launch {
            repository.addToCart(newCart)

        }
    }



}