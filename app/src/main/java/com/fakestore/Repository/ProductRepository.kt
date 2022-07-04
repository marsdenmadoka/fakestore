package com.fakestore.Repository

import androidx.room.withTransaction
import com.fakestore.Network.api.StoreApi
import com.fakestore.Room.CartDao
import com.fakestore.Room.CartEntity
import com.fakestore.Room.ProductDatabase
import com.fakestore.util.Resource
import com.fakestore.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: StoreApi,
    private val db: ProductDatabase
) {
    private val productDao = db.productDao()//we don't need to inject this
    private val cartDao = db.cartDao()

    val searchQuery = MutableStateFlow("")

    private val productsFlow = searchQuery.flatMapConcat {
        productDao.getAllProducts(it)
    }

    fun getProducts() = networkBoundResource(
        query = {/**return list of products**/
            productsFlow
            //productDao.getAllProducts() //productDao.getAllProducts("")
        },
        fetch = { //fetch product from net
            delay(2000)
            api.getProducts()
        },
        saveFetchResult = { products -> /** save/update fetch data to sql lite*/
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

    /**not a suspend function since we didn't call our getAllCart in our Dao as suspend**/
   fun getCartItems():Flow<List<CartEntity>> = cartDao.getAllCartItems()
    
}






//    suspend fun  addToApi(product: CartEntity){
//        api.sendToCartToApi
//    }