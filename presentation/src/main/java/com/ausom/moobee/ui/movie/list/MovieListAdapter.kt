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