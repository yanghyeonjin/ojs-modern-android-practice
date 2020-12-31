package com.example.ojsmodernandroidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.ojsmodernandroidpractice.databinding.ActivityMainBinding
import com.example.ojsmodernandroidpractice.models.Todo
import com.example.ojsmodernandroidpractice.utils.AppDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        )
            .allowMainThreadQueries()
            .build()

        // 값이 변경되는 것을 감지하여 UI 자동 업데이트를 도와준다!
        db.todoDao().getAll().observe(this, Observer { todos ->
            mBinding.tvResult.text = todos.toString()
        })


        // 할 일 추가 버튼 클릭
        mBinding.btnAdd.setOnClickListener {
            val newTodo = Todo(title = mBinding.etTodo.text.toString())

            // 데이터베이스에 insert
            db.todoDao().insert(newTodo)
        }
    }
}