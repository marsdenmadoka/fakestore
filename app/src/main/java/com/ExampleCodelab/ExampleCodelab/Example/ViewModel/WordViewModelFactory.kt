//package com.ExampleCodelab.ExampleCodelab.ViewModel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.ExampleCodelab.ExampleCodelab.Repository.WordRepository
//
//
////implemented a ViewModelProvider.Factory that gets as a parameter the dependencies needed to create WordViewModel: the WordRepository.
//class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return WordViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
