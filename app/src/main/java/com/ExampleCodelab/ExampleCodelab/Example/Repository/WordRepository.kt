//package com.ExampleCodelab.ExampleCodelab.Repository
//
//import androidx.annotation.WorkerThread
//import com.ExampleCodelab.ExampleCodelab.Room.WordDao
//import com.ExampleCodelab.ExampleCodelab.Room.WordEntity
//import kotlinx.coroutines.flow.Flow
///**we build the room first then the repository and later the view model
//Declares the DAO as a private property in the constructor. Pass in the DAO  instead of the whole database, since the DAO contains all the read/write methods for the database.
//**/
//class WordRepository(private val wordDao: WordDao) {
//
//    // Room executes all queries on a separate thread. Observed Flow will notify the observer when the data has changed.
//    val allWords: Flow<List<WordEntity>> = wordDao.getAlphabetizedWords() //you can do this because of how you defined the getAlphabetizedWords method to return Flow i
//
//    /** By default Room runs suspend queries off the main thread, therefore, we don't need to
//    implement anything else to ensure we're not doing long running database work  off the main thread.**/
//
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun insert(word: WordEntity) {
//        wordDao.insert(word)
//    }
//}
//
////Why use a Repository? //A Repository manages queries and allows you to use multiple backends.
////the Repository implements the logic for deciding whether to fetch data from a network or use results cached in a local database.
