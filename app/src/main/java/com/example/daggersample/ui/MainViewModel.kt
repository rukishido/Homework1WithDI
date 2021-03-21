package com.example.daggersample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggersample.data.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {

    private val _students = mutableListOf<Student>()
    val students: List<Student> = _students

    init {
        getAllStudents()
    }

    fun getAllStudents() {
        viewModelScope.launch {
            _students.clear()
            _students.addAll(studentRepository.getAllStudents())
        }
    }

    private fun addNewStudent(student: Student) {
        viewModelScope.launch {
            val studentId = studentRepository.addStudent(student)
            _students.add(
                Student(
                    id = studentId,
                    name = student.name,
                    surname = student.surname,
                    grade = student.grade,
                    birthDateYear = student.birthDateYear
                )
            )
        }
    }

    fun getStudentFromString(studentInfo: String): ValidationResult {
        val studentFields = studentInfo.split("\\s+".toRegex())
        val validationResult = isStudentFieldsValid(studentFields)
        return if (validationResult == ValidationResult.ALL_VALID) {
            addNewStudent(
                Student(
                    name = studentFields[0],
                    surname = studentFields[1],
                    grade = studentFields[2],
                    birthDateYear = studentFields[3]
                )
            )
            ValidationResult.ALL_VALID
        } else {
            validationResult
        }
    }

    private fun isStudentFieldsValid(fields: List<String>): ValidationResult {
        return when {
            fields.size != 4 -> ValidationResult.MISSING_FIELDS
            !fields[0].matches("[a-zA-Z]{3,}".toRegex()) -> ValidationResult.NAME
            !fields[1].matches("[a-zA-Z]{3,}".toRegex()) -> ValidationResult.SURNAME
            !validateGrade(fields[2]) -> ValidationResult.GRADE
            !validateYear(fields[3]) -> ValidationResult.YEAR
            else -> ValidationResult.ALL_VALID
        }
    }

    private fun validateYear(yearString: String): Boolean {
        return yearString.matches("[1-2][0-9][0-9][0-9]".toRegex()) &&
                yearString.toInt() in 1930..Calendar.getInstance().get(Calendar.YEAR)
    }

    private fun validateGrade(gradeString: String): Boolean {
        return gradeString.matches("[1]?[0-9]\\D".toRegex()) &&
                gradeString.replace("\\D".toRegex(), "").toInt() in 1..11
    }

    enum class ValidationResult {
        MISSING_FIELDS,
        NAME,
        SURNAME,
        GRADE,
        YEAR,
        ALL_VALID
    }
}
