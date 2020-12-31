package com.example.ojsmodernandroidpractice.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    var title: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}