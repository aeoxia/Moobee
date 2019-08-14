package com.ausom.domain.interactor

import com.ausom.domain.common.ResultWrapper
import com.ausom.domain.model.Movie
import com.ausom.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject

class GetMovies @Inject constructor(
    var repository: Repository
): ConsumerBaseInteractor<List<Movie>>(){

    override fun dispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
    override suspend fun <P> source(params: Array<P>): ReceiveChannel<ResultWrapper<Exception, List<Movie>>> {
        return repository.getMovies()
    }

    override suspend fun build(data: ResultWrapper<Exception, List<Movie>>): ResultWrapper<Exception, List<Movie>> {
        return data
    }
}