//package com.ExampleCodelab.ExampleCodelab.Room
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import kotlinx.coroutines.flow.Flow
//
////however we create the entity class first then the dao
////WordDao is an interface; DAOs must either be interfaces or abstract classes.
//@Dao
//interface WordDao {
//    @Query("SELECT * FROM word_table ORDER BY  word ASC")
//    fun getAlphabetizedWords(): Flow<List<WordEntity>>// A method to get all the words and have it return a List of Words To observe data changes you will use Flow from kotlinx-coroutines
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)//ignores a new word if it's exactly the same as one already in the list
//    suspend fun insert(word: WordEntity)
//
//    @Query("DELETE FROM word_table")
//    suspend fun deleteAll()
//}
//
////we build the room first then the repository and later the view model
