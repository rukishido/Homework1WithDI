package com.example.daggersample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDAO {

    @Query("SELECT * FROM student")
    suspend fun getAll(): List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student) : Long
}