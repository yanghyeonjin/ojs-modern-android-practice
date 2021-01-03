package com.example.ojsmodernandroidpractice.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ojsmodernandroidpractice.R
import com.example.ojsmodernandroidpractice.adapters.TodoAdapter
import com.example.ojsmodernandroidpractice.databinding.FragmentTodoBinding
import com.example.ojsmodernandroidpractice.models.Todo
import com.example.ojsmodernandroidpractice.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoFragment : Fragment() {
    private var _mBinding: FragmentTodoBinding? = null
    private val mBinding get() = _mBinding!!

    private lateinit var mContext: Context

    private lateinit var todoAdapter: TodoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mBinding = FragmentTodoBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // UI와 비즈니스 로직의 분리를 위해 ViewModel 사용
        val mainViewModel: MainViewModel by viewModels() // 또는 ViewModelProvider(this).get(MainViewModel::class.java)

        // 리사이클러뷰 셋팅
        todoAdapter = TodoAdapter()
        mBinding.recyclerViewTodos.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
            adapter = todoAdapter
        }

        // 값이 변경되는 것을 감지하여 UI 자동 업데이트를 도와준다!
        mainViewModel.getAll().observe(viewLifecycleOwner, Observer { todos ->
            todoAdapter.setList(todos as ArrayList<Todo>)
        })


        // 할 일 추가 버튼 클릭
        mBinding.btnAdd.setOnClickListener {
            val newTodo = Todo(title = mBinding.etTodo.text.toString())

            // 데이터베이스에 insert
            // 데이터베이스 조작은 Main Thread(UI Thread)가 아닌 Worker Thread (Background Thread)에서 작동되어야 한다.
            lifecycleScope.launch(Dispatchers.IO) {
                mainViewModel.insert(newTodo)

                withContext(Dispatchers.Main) {
                    mBinding.etTodo.setText("")
                }
            }
        }

        // 다음 버튼 클릭
//        mBinding.btnNext.setOnClickListener {
//            // safe-args를 사용한다.
//            // argument를 적용시킨 action을 만든 후 navigate 한다.
//            val action = TodoFragmentDirections.actionFromTodoToDetails(text = "Hello!!!")
//            findNavController().navigate(action)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}