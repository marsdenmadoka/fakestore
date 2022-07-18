package com.fakestore.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fakestore.Repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

///N/B we always collect a flow and for livedata we observe it //however we can convert a flow into a live data ||getProducts().asLiveData()

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
    //private val state: SavedStateHandle

) : ViewModel() {

    val searchQuery = repository.searchQuery

    val products = repository.getProducts().asLiveData()
    /**we don't have to launch a coroutine to collect the flow.since we turned the flow into live data its already handled for us*/


    /**we can also decide to use flow and collect it our ui
     * val products = repository.getProducts()
    .stateIn(viewModelScope, SharingStarted.Lazily, null)*/

    fun getByElectronicsCategory() {
        viewModelScope.launch {
            products.observeForever {
                it.data?.filter { result ->
                    result.category == "electronics"
                }
            }
        }
    }


    val getcart =
        repository.getCartItems() // To optimize and share a flow when multiple consumers collect at the same time, use the shareIn operator.
            .stateIn(viewModelScope, SharingStarted.Lazily, null)


//    fun onSingleProductClicked(product: ProductEntity)=viewModelScope.launch {
//        val action = HomeFragmentDirections.actionHomeFragmentToProductItemFragment(product)
//        findNavController(fragment = Fragment(R.layout.fragment_home)).navigate(action)
//    }


}


//suspend fun getElectronic() {
//    repository.getProducts().collectLatest { result ->
//        result.data?.filter { it.category == "electronics" }
//    }
//}
//
//
//fun getElectronics() {
//    products.value.apply {
//        this?.data?.filter {
//            it.category == "electronics"
//        }
//    }
//}
