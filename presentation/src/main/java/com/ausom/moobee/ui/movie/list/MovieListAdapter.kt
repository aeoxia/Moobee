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

package com.ausom.moobee.ui.movie.list

import android.util.SparseIntArray
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.ausom.moobee.BR
import com.ausom.moobee.R
import com.ausom.moobee.di.fragment.FragmentScope
import com.ausom.moobee.ui.base.BaseAdapter
import com.ausom.moobee.ui.base.BaseViewHolder
import com.ausom.moobee.ui.movie.list.item.MovieListItemViewModel
import javax.inject.Inject

@FragmentScope
class MovieListAdapter @Inject constructor() : BaseAdapter() {

   /**
    *
    *  Manage span depending on model
    *
    * */
    val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (dataSet[position]) {
                is MovieListItemViewModel -> 1
                else -> 2
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, payloads: MutableList<Any>) {
        holder.itemView.findViewById<TextView>(R.id.movieTitle)?.isSelected = true
        return super.onBindViewHolder(holder, position, payloads)
    }

    /**
     *
     *  Loaders and empty layouts can be placed here
     *
     * */
    override val viewBindingVariableMap = SparseIntArray().apply {
        listOf(
                R.layout.movie_item_layout
        ).forEach { res ->
            put(res, BR.viewModel)
        }
    }
}