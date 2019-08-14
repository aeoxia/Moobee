package com.ausom.moobee.ui.movie.list

import androidx.lifecycle.MutableLiveData
import com.ausom.domain.common.fold
import com.ausom.domain.interactor.GetMovies
import com.ausom.moobee.model.mapper.MovieModelMapper
import com.ausom.moobee.ui.base.BaseViewModel
import com.ausom.moobee.ui.base.ListItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModelImpl @Inject constructor(
    var getMovies: GetMovies,
    var movieModelMapper: MovieModelMapper
) : MovieListViewModel, BaseViewModel() {

    override val items = MutableLiveData<List<ListItem>>()

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onInitialize() {
        super.onInitialize()
        refresh()
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    override fun refresh(): Job  = launch(bgDispatcher){
        getMovies.execute().consumeEach {result->
            result.fold({
                throw it
            },{
                items.postValue(movieModelMapper.transformToViewModel(it, this@MovieListViewModelImpl))
            })
        }
    }
}