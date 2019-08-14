package com.ausom.moobee.ui.movie.detail

import com.ausom.moobee.model.MovieModel
import com.ausom.moobee.ui.base.BaseViewModel
import javax.inject.Inject

class MovieDetailViewModelImpl @Inject constructor(
    val movieDetailParameter: MovieDetailParameter
    ): BaseViewModel(), MovieDetailViewModel {
    override val movieModel: MovieModel = movieDetailParameter.movieModel
}