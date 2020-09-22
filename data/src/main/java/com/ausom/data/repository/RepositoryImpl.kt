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

package com.ausom.data.repository

import com.ausom.data.entity.MovieCategoryEntity
import com.ausom.data.entity.mapper.MovieEntityMapper
import com.ausom.data.net.GoogleDataSource
import com.ausom.domain.common.ResultWrapper
import com.ausom.domain.common.flatMap
import com.ausom.domain.common.fold
import com.ausom.domain.common.map
import com.ausom.domain.model.Movie
import com.ausom.domain.repository.Repository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val googleDataSource: GoogleDataSource,
    private val movieEntityMapper: MovieEntityMapper
): Repository {

    @ExperimentalCoroutinesApi
    override suspend fun getMovies(): Deferred<ResultWrapper<Exception, List<Movie>>> =
        GlobalScope.async {
            googleDataSource
                .getMovies()
                .await()
                .map {
                    it.categories
                        .flatMap(MovieCategoryEntity::movies)
                        .map(movieEntityMapper::toDomain)
                }
        }
}