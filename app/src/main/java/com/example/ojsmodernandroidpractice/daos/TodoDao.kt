package com.example.ojsmodernandroidpractice.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ojsmodernandroidpractice.models.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): LiveData<List<Todo>> // List를 관찰하기 위해 LiveData로 감싸준다.

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}