package com.fakestore.Repository

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.room.withTransaction
import com.fakestore.Network.api.data.StoreApi
import com.fakestore.Room.CartEntity
import com.fakestore.Room.ProductDatabase
import com.fakestore.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: StoreApi,
    private val db: ProductDatabase,
    private val state: SavedStateHandle
) {
    private val productDao = db.productDao()//we don't need to inject this
    private val cartDao = db.cartDao()


    //val searchQuery = MutableStateFlow("")
    val searchQuery = state.getLiveData("searchQuery", "")

    /**we want to use the save state to restore/save our search*/

    val productsFlow = searchQuery.asFlow().flatMapLatest {
        productDao.getAllProducts(it)
    }

    fun getProducts() = networkBoundResource(
        query = {
            /**return list of products **/ //productDao.getAllProducts()
            productsFlow
        },
        fetch = {
            /**fetch product from net**/
            delay(2000)
            // api.getProductsByCategory()
            api.getProducts()

        },
        saveFetchResult = { products ->
            /** save/update fetch data to sql lite*/
            db.withTransaction {
                /**we delete the old data and insert the newly fetched*/
                productDao.deleteAllProducts()
                productDao.insertProducts(products)
            }
        }

    )

    /**not a suspend function since we didn't call our getAllCart in our Dao as suspend it is also coming from a loca;db**/
    fun getCartItems(): Flow<List<CartEntity>> = cartDao.getAllCartItems()

    /**saving our cartItems to our local database**/
    suspend fun addToCart(product: CartEntity) {
        cartDao.insertCartItems(product)
    }
    /**remove cartItem*/
    suspend fun deleteCartItem(cartItem: CartEntity){
        cartDao.delete(cartItem)
    }

}

