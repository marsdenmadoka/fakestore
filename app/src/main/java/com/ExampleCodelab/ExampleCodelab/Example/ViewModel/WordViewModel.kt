//package com.ExampleCodelab.ExampleCodelab.ViewModel
//
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.ExampleCodelab.ExampleCodelab.Repository.WordRepository
//import com.ExampleCodelab.ExampleCodelab.Room.WordEntity
//
//
////A ViewModel holds your app's UI data in a lifecycle-conscious way that survives configuration changes.
//
//class WordViewModel(private val repository: WordRepository) : ViewModel() {
//
//    /** Using LiveData and caching what allWords returns has several benefits: We can put an observer on the data (instead of polling for changes) and only update the
//    the UI when the data actually changes.  Repository is completely separated from the UI through the ViewModel.**/
//
//    val allWords: LiveData<List<WordEntity>> =
//        repository.allWords.asLiveData() //added a public LiveData member variable to cache the list of words You then converted the Flow to LiveData by calling asLiveData().
//
//    /**
//     * Launching a new coroutine to insert the data in a non-blocking way
//     */
//
//    fun insert(word: WordEntity) =     // calling the Repository's insert() method.
//        viewModelScope.launch {
//            repository.insert(word)
//        }
//}
//
//
//// By using viewModels and ViewModelProvider.Factory,the framework will take care of the lifecycle of the ViewModel. It will survive configuration
//// changes and even if the Activity is recreated, you'll always get the right instance of the WordViewModel class.