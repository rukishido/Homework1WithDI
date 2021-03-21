package com.example.daggersample.di

import android.content.Context
import androidx.room.Room
import com.example.daggersample.data.StudentDAO
import com.example.daggersample.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideStudentDAO(appdb:AppDatabase):StudentDAO{
        return appdb.studentDAO()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext:Context):AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }
}