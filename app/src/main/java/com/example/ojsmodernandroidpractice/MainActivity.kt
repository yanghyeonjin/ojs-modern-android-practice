package com.example.ojsmodernandroidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.ojsmodernandroidpractice.databinding.ActivityMainBinding
import com.example.ojsmodernandroidpractice.models.Todo
import com.example.ojsmodernandroidpractice.utils.AppDatabase
import com.example.ojsmodernandroidpractice.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // UI와 비즈니스 로직의 분리를 위해 ViewModel 사용
        val mainViewModel: MainViewModel by viewModels() // 또는 ViewModelProvider(this).get(MainViewModel::class.java)


        // 값이 변경되는 것을 감지하여 UI 자동 업데이트를 도와준다!
        mainViewModel.getAll().observe(this, Observer { todos ->
            mBinding.tvResult.text = todos.toString()
        })


        // 할 일 추가 버튼 클릭
        mBinding.btnAdd.setOnClickListener {
            val newTodo = Todo(title = mBinding.etTodo.text.toString())

            // 데이터베이스에 insert
            // 데이터베이스 조작은 Main Thread(UI Thread)가 아닌 Worker Thread (Background Thread)에서 작동되어야 한다.
            lifecycleScope.launch(Dispatchers.IO) {
                mainViewModel.insert(newTodo)
            }
        }
    }
}