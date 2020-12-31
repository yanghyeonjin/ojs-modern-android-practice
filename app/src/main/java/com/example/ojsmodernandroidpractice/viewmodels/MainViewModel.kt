package com.example.ojsmodernandroidpractice.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.ojsmodernandroidpractice.models.Todo
import com.example.ojsmodernandroidpractice.utils.AppDatabase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    // suspend 키워드가 붙으면 -> 코루틴 스코프 안에서 실행되어야 한다!
    // 난 비동기로 처리 되어야 해 -> suspend 붙여주고 -> 코루틴 스코프 안에서 실행
    suspend fun insert(todo: Todo) {
        db.todoDao().insert(todo = todo)
    }
}