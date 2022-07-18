package com.fakestore.ViewModel.AuthViewModel

import androidx.lifecycle.*
import com.fakestore.Network.Response.LoginResponse
import com.fakestore.Repository.AuthRepository.AuthRepository
import com.fakestore.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    /**
     * Using Live data //live data version
    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse**/


    private val _loginResponse = MutableStateFlow<Resource<LoginResponse>>(Resource.Loading())
    val loginResponse = _loginResponse.asStateFlow().asLiveData()


    fun loginUser(
        username: String,
        password:String
    )= viewModelScope.launch {
  _loginResponse.value =  Resource.Loading()
   _loginResponse.value = repository.login(username,password)

    }
}