package com.example.fakestore.Example.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(WordEntity::class), version = 1, exportSchema = false)
 abstract class WordRoomDatabase : RoomDatabase() //The database class for Room must be abstract and extend RoomDatabase
{
    abstract fun wordDao(): WordDao
    companion object{  // Singleton prevents multiple instances of database opening at the  same time.

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            // if the INSTANCE is not null, then return it, if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
