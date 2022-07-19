package com.fakestore.Repository

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.room.withTransaction
import com.fakestore.Network.api.StoreApi
import com.fakestore.Room.CartEntity
import com.fakestore.Room.ProductDatabase
import com.fakestore.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: StoreApi,
    private val db: ProductDatabase,
    private val state: SavedStateHandle
) {
    private val productDao = db.productDao()//we don't need to inject this
    private val cartDao = db.cartDao()


    //val searchQuery = MutableStateFlow("")
    val searchQuery = state.getLiveData("searchQuery","")/**we want to use the save state to restore/save our search*/

    val productsFlow = searchQuery.asFlow().
        flatMapLatest {
            productDao.getAllProducts(it)
    }

    fun getProducts() = networkBoundResource(
        query = {
            /**return list of products**//**fun getProducts(category: String) =**/
            productsFlow    ///productDao.getAllProducts()
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

    /**saving our cartItems to our local database**/
    suspend fun addToCart(product: CartEntity) {
        cartDao.insertCartItems(product)
    }

    /**not a suspend function since we didn't call our getAllCart in our Dao as suspend it is also coming from a loca;db**/
    fun getCartItems(): Flow<List<CartEntity>> = cartDao.getAllCartItems()

}
    


//    val _SelectedCategory = MutableStateFlow("All")
//   fun setCategory(value: String){
//       _SelectedCategory.value=value
//   }
