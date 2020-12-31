package com.example.ojsmodernandroidpractice.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ojsmodernandroidpractice.daos.TodoDao
import com.example.ojsmodernandroidpractice.models.Todo

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}