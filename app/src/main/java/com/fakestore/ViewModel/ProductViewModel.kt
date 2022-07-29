package com.fakestore.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fakestore.Network.Response.UserResponse
import com.fakestore.Repository.ProductRepository
import com.fakestore.Repository.UserRepository
import com.fakestore.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

///N/B we always collect a flow and for livedata we observe it //however we can convert a flow into a live data ||getProducts().asLiveData()

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val userRepository: UserRepository,

    ) : ViewModel() {

    val searchQuery = repository.searchQuery

    /**we don't have to launch a coroutine to collect the flow.since we turned the flow into live data its already handled for us*/
    val products = repository.getProducts().asLiveData()

    /**we can also decide to use flow and collect it our ui
     val products = repository.getProducts().stateIn(viewModelScope, SharingStarted.Lazily, null)*/


    /**getting user **//**NOT WORKING*/
    private val _user = MutableStateFlow<Resource<List<UserResponse>>>(Resource.Loading())
    val user = _user.asStateFlow().asLiveData()
    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading()
        _user.value = userRepository.getUser()
    }






    /**getting items of electronics category*//**NOT WORKING*/
    fun getElectronics (){
        viewModelScope.launch {
            products.value?.data?.filter { it.category == "electronics" }
        }
    }


}






//    fun getElectronic (){
//        viewModelScope.launch {
//            repository.getProducts().
//            collectLatest {
//                it.data?.filter {
//                    it.category == "electronics" }
//            }
//        }
//    }


//
//
//fun getElectronics() {
//    products.value.apply {
//        this?.data?.filter {
//            it.category == "electronics"
//        }
//    }
//}
