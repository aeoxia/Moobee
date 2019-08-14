package com.ausom.moobee.navigation

import androidx.navigation.NavDirections
import com.ausom.moobee.ui.movie.detail.MovieDetailParameter
import com.ausom.moobee.ui.movie.list.MovieListFragmentDirections

sealed class NavigationRequest(val destination: NavDirections) {
    data class ListToDetail(val param: MovieDetailParameter) :
        NavigationRequest(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(param))
}