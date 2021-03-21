package com.example.daggersample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "grade") val grade: String,
    @ColumnInfo(name = "birth_date_year") val birthDateYear: String
) {
    override fun toString(): String {
        return "$id $surname $name $grade $birthDateYear"
    }
}