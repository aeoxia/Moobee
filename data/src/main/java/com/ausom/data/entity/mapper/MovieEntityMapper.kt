package com.ausom.data.entity.mapper

import com.ausom.data.entity.MovieEntity
import com.ausom.domain.model.Movie

class MovieEntityMapper constructor(
    var videoApiBaseUrl: String)
    : EntityMapper<MovieEntity, Movie> {

    override fun toEntity(from: Movie): MovieEntity {
        return MovieEntity(
                title = from.title,
                subtitle = from.subtitle,
                thumb = from.thumb,
                poster = from.poster,
                sources = listOf(from.source)
        )
    }

    override fun toDomain(from: MovieEntity): Movie {
        return Movie(
                title = requireNotNull(from.title),
                subtitle = requireNotNull(from.subtitle).let { s -> "$s\n\n$s\n\n$s" },
                thumb = "$videoApiBaseUrl${from.thumb}",
                poster = "$videoApiBaseUrl${from.poster}",
                source = requireNotNull(from.sources?.firstOrNull())
        )
    }
}