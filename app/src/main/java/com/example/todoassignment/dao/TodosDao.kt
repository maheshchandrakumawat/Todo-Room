package com.example.todoassignment.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoassignment.models.Todos
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDao {

    @Query("SELECT * FROM todos")
    fun selectAll(): Flow<List<Todos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Todos)

    @Delete
    suspend fun delete(book: Todos)
}