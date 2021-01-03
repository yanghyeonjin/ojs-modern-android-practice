package com.example.ojsmodernandroidpractice.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ojsmodernandroidpractice.databinding.RecyclerviewItemTodoBinding
import com.example.ojsmodernandroidpractice.models.Todo
import com.example.ojsmodernandroidpractice.viewholders.TodoViewHolder

class TodoAdapter : RecyclerView.Adapter<TodoViewHolder>() {
    private var todos = ArrayList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            RecyclerviewItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setList(list: ArrayList<Todo>) {
        this.todos = list
        notifyDataSetChanged()
    }
}