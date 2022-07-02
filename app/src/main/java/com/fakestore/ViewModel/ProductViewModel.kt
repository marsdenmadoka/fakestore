package com.fakestore.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fakestore.Repository.ProductRepository
import com.fakestore.Room.ProductDatabase
import com.fakestore.Room.ProductEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel  @Inject constructor(
    repository: ProductRepository,
   //private val stete: SavedStateHandle
) : ViewModel() {

    val searchQuery=repository.searchQuery
    val products = repository.getProducts().asLiveData()/**we don't have to launch a coroutine to collect the flow.since we turned the flow into live data its already handled for us*/


    fun onProductSelected(product: ProductEntity) = viewModelScope.launch {

        }


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