package com.ausom.moobee.ui.base

import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    protected var dataSet = listOf<ListItem>()
    protected abstract val viewBindingVariableMap: SparseIntArray

    open fun updateItems(newDataSet: List<ListItem>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(dataSet, newDataSet))
        diffResult.dispatchUpdatesTo(this)
        dataSet = newDataSet
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val variableId = viewBindingVariableMap[viewType]
        return BaseViewHolder(
                variableId,
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        viewType,
                        parent,
                        false
                )
        )
    }

    override fun getItemViewType(position: Int): Int = dataSet[position].type

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        return holder.bind(dataSet[position])
    }

    class DiffCallback(private val oldItems: List<ListItem>,
                       private val newItems: List<ListItem>)
        : DiffUtil.Callback() {

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldItems[oldPosition].id == newItems[newPosition].id
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return areItemsTheSame(oldPosition, newPosition)
        }

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size
    }
}