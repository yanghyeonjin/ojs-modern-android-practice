package com.example.ojsmodernandroidpractice.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.ojsmodernandroidpractice.databinding.RecyclerviewItemTodoBinding
import com.example.ojsmodernandroidpractice.models.Todo

class TodoViewHolder(binding: RecyclerviewItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    private val tvTitle = binding.tvTitle

    fun bind(item: Todo) {
        this.tvTitle.text = item.title
    }
}