package com.example.daggersample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.daggersample.data.Student
import com.example.daggersample.data.StudentDAO

@Database(entities = [Student::class], version = AppDatabase.VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDAO(): StudentDAO


    companion object {
        const val VERSION = 1
        const val NAME = "appdatabase.db"

    }
}