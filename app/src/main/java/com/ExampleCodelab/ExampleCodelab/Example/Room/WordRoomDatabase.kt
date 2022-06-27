//package com.ExampleCodelab.ExampleCodelab.Room
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.sqlite.db.SupportSQLiteDatabase
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//// Annotates class to be a Room Database with a table (entity) of the Word class
//@Database(entities = arrayOf(WordEntity::class), version = 1, exportSchema = false)
// abstract class WordRoomDatabase : RoomDatabase() //The database class for Room must be abstract and extend RoomDatabase
//{
//    abstract fun wordDao(): WordDao
//
//    private class WordDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    var wordDao = database.wordDao()
//
//                    // Delete all content here.
//                    wordDao.deleteAll()
//
//                    // Add sample words.
//                    var word = WordEntity(1,"Hello")
//                    wordDao.insert(word)
//                    word = WordEntity(2,"World!")
//                    wordDao.insert(word)
//
//                    // TODO: Add your own words!
//                    word = WordEntity(3,"TODO!")
//                    wordDao.insert(word)
//                }
//            }
//        }
//    }
//
//    companion object{  // Singleton prevents multiple instances of database opening at the  same time.
//
//        @Volatile
//        private var INSTANCE: WordRoomDatabase? = null
//
//        fun getDatabase(context: Context): WordRoomDatabase {
//            // if the INSTANCE is not null, then return it, if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    WordRoomDatabase::class.java,
//                    "word_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }
//}
