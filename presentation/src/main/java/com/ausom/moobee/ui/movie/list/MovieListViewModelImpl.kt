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

import androidx.lifecycle.MutableLiveData
import com.ausom.domain.common.fold
import com.ausom.domain.interactor.GetMovies
import com.ausom.moobee.model.mapper.MovieModelMapper
import com.ausom.moobee.ui.base.BaseViewModel
import com.ausom.moobee.ui.base.ListItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
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
    override fun refresh(): Job = launch(bgDispatcher) {
        getMovies.executeAsync<Unit>().await().fold({
            throw it
        }, {
            items.postValue(movieModelMapper.transformToViewModel(it, this@MovieListViewModelImpl))
        })
    }
}