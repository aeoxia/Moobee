package com.ausom.data.repository

import com.ausom.data.entity.MovieCategoryEntity
import com.ausom.data.entity.mapper.MovieEntityMapper
import com.ausom.data.net.GoogleDataSource
import com.ausom.domain.common.ResultWrapper
import com.ausom.domain.common.fold
import com.ausom.domain.model.Movie
import com.ausom.domain.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
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
    override suspend fun getMovies(): ReceiveChannel<ResultWrapper<Exception, List<Movie>>> =
        GlobalScope.produce {
            send(
                ResultWrapper.build {
                    googleDataSource
                        .getMovies()
                        .receive()
                        .fold(
                            { throw it },
                            {
                                it.categories
                                    .flatMap(MovieCategoryEntity::movies)
                                    .map(movieEntityMapper::toDomain)
                            }
                        )
                }
            )
        }
}