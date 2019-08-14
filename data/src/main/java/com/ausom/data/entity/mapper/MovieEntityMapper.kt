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