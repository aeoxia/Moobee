package com.ausom.moobee.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(
        private val variableId: Int,
        private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem?) {
        binding.apply {
            setVariable(variableId, item)
            executePendingBindings()
        }
    }
}