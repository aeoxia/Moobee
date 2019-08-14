package com.ausom.moobee.ui.movie.list.item

import com.ausom.moobee.R
import com.ausom.moobee.model.MovieModel
import com.ausom.moobee.navigation.NavigationRequest
import com.ausom.moobee.ui.base.ListItem
import com.ausom.moobee.ui.movie.detail.MovieDetailParameter
import com.ausom.moobee.ui.movie.list.MovieListViewModel

class MovieListItemViewModel(val movieModel: MovieModel, val parent: MovieListViewModel) : ListItem {
    override val id = movieModel.id
    override val type = R.layout.movie_item_layout

    fun onClick() {
        parent.requestNavigation(NavigationRequest.ListToDetail(MovieDetailParameter(movieModel)))
    }
}