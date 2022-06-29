package com.fakestore.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fakestore.Repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    repository: ProductRepository
) : ViewModel() {
    val products = repository.getProducts().asLiveData()/**we don't have to launch a coroutine to collect the flow.since we turned the flow into live data its already handled for us*/


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