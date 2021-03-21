package com.example.daggersample.ui

import com.example.daggersample.data.Student
import com.example.daggersample.data.StudentDAO
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val studentDAO: StudentDAO
) {
    suspend fun getAllStudents() = studentDAO.getAll()

    suspend fun addStudent(student: Student) : Long {
        return studentDAO.insert(student)
    }
}