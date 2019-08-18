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

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ausom.moobee.R
import com.ausom.moobee.databinding.FragmentMovieListBinding
import com.ausom.moobee.di.Injectable
import com.ausom.moobee.extensions.observe
import com.ausom.moobee.navigation.NavigationRequest
import com.ausom.moobee.ui.base.BaseFragment
import com.ausom.moobee.ui.base.ListItem
import javax.inject.Inject

class MovieListFragment : BaseFragment<FragmentMovieListBinding,MovieListViewModel>(), Injectable {

    @Inject
    override lateinit var viewModel: MovieListViewModel
    @Inject
    internal lateinit var movieListAdapter: MovieListAdapter
    override val layoutRes: Int = R.layout.fragment_movie_list

    private fun onItemsLoaded(items: List<ListItem>){
        movieListAdapter.updateItems(items)
    }

    override fun onNavigationRequest(navigationRequest: NavigationRequest) {
        when (navigationRequest) {
            is NavigationRequest.ListToDetail -> navigateToDetail(navigationRequest)
        }
    }

    private fun navigateToDetail(navigationRequest: NavigationRequest.ListToDetail) {
        navigate(navigationRequest.destination)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.items.observe(this,::onItemsLoaded)
    }

    override fun onBindingCreated(binding: FragmentMovieListBinding) {
        binding.viewModel = viewModel
        binding.movieList.apply {
            adapter = movieListAdapter
            (layoutManager as GridLayoutManager)
                .spanSizeLookup = movieListAdapter.spanSizeLookup
        }
    }
}