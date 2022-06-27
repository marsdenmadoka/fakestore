package com.fakestore.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakestore.Network.api.StoreApi
import com.fakestore.Room.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    api:StoreApi
): ViewModel() {

    private val storeLivedata = MutableLiveData<List<ProductEntity>>()
    val stores  : LiveData<List<ProductEntity>> = storeLivedata
    init{
   viewModelScope.launch{
       val store = api.getProducts()
       storeLivedata.value =store
       
   }
    }
}