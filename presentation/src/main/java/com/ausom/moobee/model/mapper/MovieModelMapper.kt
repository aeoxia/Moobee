package com.ausom.moobee.model.mapper

import com.ausom.domain.model.Movie
import com.ausom.moobee.model.MovieModel
import com.ausom.moobee.ui.movie.list.MovieListViewModel
import com.ausom.moobee.ui.movie.list.item.MovieListItemViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieModelMapper @Inject constructor()
    : ModelMapper<MovieModel, Movie> {
    fun transformToViewModel(from: List<Movie>, parent: MovieListViewModel): List<MovieListItemViewModel> {
        return from.asSequence()
                .map(::toModel)
                .map { movieModels -> MovieListItemViewModel(movieModels, parent) }
                .toList()
    }

    override fun toModel(from: Movie): MovieModel {
        return MovieModel(
                title = from.title,
                subtitle = from.subtitle,
                thumb = from.thumb,
                poster = from.poster,
                source = from.source
        )
    }

    override fun toDomain(from: MovieModel): Movie {
        return Movie(
                title = from.title,
                subtitle = from.subtitle,
                thumb = from.thumb,
                poster = from.poster,
                source = from.source
        )
    }
}