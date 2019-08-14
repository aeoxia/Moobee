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