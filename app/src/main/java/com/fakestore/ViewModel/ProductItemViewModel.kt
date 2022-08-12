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
    private val state: SavedStateHandle,
    private val repository: ProductRepository,
) : ViewModel() {

    companion object {
        private const val PRODUCT_ITEM = "ProductItem"
        private const val PRODUCT_ITEM_NAME = "productItemName"
        private const val PRODUCT_ITEM_CATEGORY = "productItemCategory"
        private const val PRODUCT_ITEM_IMAGE = "productItemImage"
        private const val PRODUCT_ITEM_PRICE = "productItemPrice"
        private const val PRODUCT_ITEM_DESCRIPTION = "productItemDescription"
    }

    private val product =
        state.get<ProductEntity>(PRODUCT_ITEM) //key of te argument MUST be the same with that from the navgraph

    var productItemName = state.get<String>(PRODUCT_ITEM_NAME) ?: product?.title ?: ""
        set(value) { /**storing it in a save instance state*/
            field = value
            state[PRODUCT_ITEM_NAME] = value
            /**state.set(PRODUCT_ITEM_NAME,value)*/
        }

    var productItemCategory = state.get<String>(PRODUCT_ITEM_CATEGORY) ?: product?.category ?: ""
        set(value) {
            field = value
            state[PRODUCT_ITEM_CATEGORY] = value
        }


    var productItemImage = state.get<String>(PRODUCT_ITEM_IMAGE) ?: product?.image ?: ""
        set(value) {
            field = value
            state[PRODUCT_ITEM_IMAGE] = value
        }


    var productItemPrice = state.get<String>(PRODUCT_ITEM_PRICE) ?: product?.price ?: ""
        set(value) {
            field = value
            state[PRODUCT_ITEM_PRICE] = value
        }

    var productItemDescription =
        state.get<String>(PRODUCT_ITEM_DESCRIPTION) ?: product?.description ?: ""
        set(value) {
            field = value
            state[PRODUCT_ITEM_DESCRIPTION] = value
        }

    fun addToCart() {
        /**adding our cartItems to our local db*//*this one is different from te one in our cartView model since here we use savedStateHandle to get the items passed to ProductItemViewFragment */
        val newCart = CartEntity(
            title = productItemName,
            category = productItemCategory,
            image = productItemImage,
            price = productItemPrice as Double
        )
        /**write code to check if the cart already exist*/

        viewModelScope.launch {
            repository.addToCart(newCart)

        }
    }


}