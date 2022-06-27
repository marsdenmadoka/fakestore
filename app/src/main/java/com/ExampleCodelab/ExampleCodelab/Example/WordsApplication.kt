//package com.ExampleCodelab.ExampleCodelab
//
//import android.app.Application
//import com.ExampleCodelab.ExampleCodelab.Repository.WordRepository
//import com.ExampleCodelab.ExampleCodelab.Room.WordRoomDatabase
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.SupervisorJob
///** our view model communicates with pur repository
// * //you can use any DI framework recommended hlt
//You want to have only one instance of the database and of the repository in your app. An easy way to achieve this is by creating them as members of the Application class. Then they will just be retrieved from the Application whenever they're needed, rather than constructed every time**/
//class WordsApplication :Application() {
//    // No need to cancel this scope as it'll be torn down with the process
//    val applicationScope = CoroutineScope(SupervisorJob())
//
//
//    // Using by lazy so the database and the repository are only created when they're needed
//    // rather than when the application starts
//    val database by lazy { WordRoomDatabase.getDatabase(this) }
//    val repository by lazy { WordRepository(database.wordDao()) }
//
//}
////https://developer.android.com/codelabs/android-room-with-a-view-kotlin#12