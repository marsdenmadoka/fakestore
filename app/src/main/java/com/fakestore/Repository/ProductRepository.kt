package com.fakestore.Repository

import androidx.room.withTransaction
import com.fakestore.Network.api.StoreApi
import com.fakestore.Room.ProductDatabase
import com.fakestore.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private  val api:StoreApi,
    private val db:ProductDatabase
) {
   private val productDao = db.productDao()//we don't need to inject this

    fun getProducts() = networkBoundResource(
        query = {//return list of products
            productDao.getAllProducts()
        },
        fetch={ //fetch product from net
            delay(2000)
            api.getProducts()
        },
        saveFetchResult = { products -> //save/update fetch data to sql lite
            db.withTransaction {
                //we delete the old data and insert the newly fetched
                 productDao.deleteAllProducts()
                productDao.insertProducts(products)
            }
        }
    )
}