package com.fakestore.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fakestore.Repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductViewModel  @Inject constructor(
    repository: ProductRepository,
   //private val stete: SavedStateHandle
) : ViewModel() {

    val searchQuery=repository.searchQuery
    val products = repository.getProducts().asLiveData()/**we don't have to launch a coroutine to collect the flow.since we turned the flow into live data its already handled for us*/



    /**no need to create CartItemViewModel we can jus use this class*/

    val getcart = repository.getCartItems()
        .stateIn(viewModelScope, SharingStarted.Lazily,null)


//    fun onProductSelected(product: ProductEntity) = viewModelScope.launch {   }
}





//    private val storeLivedata = MutableLiveData<List<ProductEntity>>()
//    val stores  : LiveData<List<ProductEntity>> = storeLivedata
//    init{
//   viewModelScope.launch{
//       val store = api.getProducts()
//       storeLivedata.value =store
//
//   }
//    }