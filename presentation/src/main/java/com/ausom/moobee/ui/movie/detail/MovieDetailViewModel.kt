package com.ausom.moobee.ui.movie.detail

import com.ausom.moobee.model.MovieModel
import com.ausom.moobee.ui.base.IViewModel

interface MovieDetailViewModel : IViewModel{
    val movieModel: MovieModel
}