package com.example.daggersample.di

import com.example.daggersample.data.StudentDAO
import com.example.daggersample.ui.StudentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun getStudentRepository(studentDAO: StudentDAO): StudentRepository {
        return StudentRepository(studentDAO)
    }
}