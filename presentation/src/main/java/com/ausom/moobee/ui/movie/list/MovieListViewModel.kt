package com.ausom.moobee.ui.movie.list

import androidx.lifecycle.LiveData
import com.ausom.moobee.ui.base.IViewModel
import com.ausom.moobee.ui.base.ListItem
import kotlinx.coroutines.Job

interface MovieListViewModel : IViewModel {
    val items: LiveData<List<ListItem>>
    fun refresh(): Job
}