package com.example.todoassignment

import android.content.Context
import androidx.room.Room
import com.example.todoassignment.database.TodosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        TodosDatabase::class.java,
        "todo_db"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: TodosDatabase) = db.todoDao()
}