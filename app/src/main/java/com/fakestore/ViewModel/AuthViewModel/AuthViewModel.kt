package com.fakestore.ViewModel.AuthViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fakestore.Network.Response.LoginResponse
import com.fakestore.Network.Response.UserResponse
import com.fakestore.Repository.AuthRepository.AuthRepository
import com.fakestore.datastore.PreferenceDataStore
import com.fakestore.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {
   // lateinit var username: String lateinit var password: String

    /**
     * Using Live data //live data version
    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
    get() = _loginResponse**/

    private val _loginResponse = MutableStateFlow<Resource<LoginResponse>>(Resource.Loading(null))
    val loginResponse = _loginResponse.asStateFlow().asLiveData()
    fun loginUser(
        username: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading(null)
        _loginResponse.value = repository.login(username, password)
    }

    /**
     //consider using this over the above one
   private val loginUser: StateFlow<Resource<LoginResponse>> = flow {
        emit(repository.login(username, password))
    }.stateIn(
        scope = viewModelScope,
        started =  WhileSubscribed(5000), //SharingStarted.Lazily,
        initialValue = Resource.Loading()
    ) **/

    suspend fun saveAccessTokens(accessToken: String) {
        repository.saveAccessTokens(accessToken)
    }
    /**SignUp*/
    private val _signupResponse = MutableStateFlow<Resource<UserResponse>>(Resource.Loading())
    val signupResponse  = _signupResponse.asStateFlow().asLiveData()

    fun signUpUser(
        email: String,
        username: String,
        password: String
    ) = viewModelScope.launch {
        _signupResponse.value = Resource.Loading()
        _signupResponse .value = repository.SignUp(email,username,password)
    }



}