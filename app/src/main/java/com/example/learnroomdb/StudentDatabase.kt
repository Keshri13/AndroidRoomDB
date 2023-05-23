package com.example.learnroomdb

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {

        @Volatile
        private var INSTANCE : StudentDatabase? = null

        fun getDatabase(context:Context):StudentDatabase{
            val tempInstance = INSTANCE
            if()
        }
    }

}