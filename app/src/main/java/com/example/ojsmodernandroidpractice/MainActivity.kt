package com.example.ojsmodernandroidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // 데이터베이스 전체 내용 가져와서 텍스트 뷰에 뿌려주기
        mBinding.tvResult.text = db.todoDao().getAll().toString()


        // 할 일 추가 버튼 클릭
        mBinding.btnAdd.setOnClickListener {
            val newTodo = Todo(title = mBinding.etTodo.text.toString())

            // 데이터베이스에 insert
            db.todoDao().insert(newTodo)

            // insert 된 내용 포함해서 다시 뿌리기
            mBinding.tvResult.text = db.todoDao().getAll().toString()
        }
    }
}