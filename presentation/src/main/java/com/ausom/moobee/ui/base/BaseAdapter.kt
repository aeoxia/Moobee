/*
 * MIT License
 *
 * Copyright (c) 2019 Jan Kennu Paz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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