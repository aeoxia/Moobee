package com.ausom.data.net

import com.ausom.data.entity.reponse.MovieResponseEntity
import com.ausom.domain.common.ResultWrapper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import javax.inject.Inject

class GoogleDataSourceImpl @Inject constructor(
    var service: GoogleApiService
) : GoogleDataSource {

    @ExperimentalCoroutinesApi
    override suspend fun getMovies(): ReceiveChannel<ResultWrapper<Exception, MovieResponseEntity>> =
        apiLaunch(service.getMovies())

    @ExperimentalCoroutinesApi
    private fun <T>apiLaunch(requester: Deferred<T>): ReceiveChannel<ResultWrapper<Exception, T>> =
        GlobalScope.produce(Dispatchers.IO){
            send(ResultWrapper.build {
               requester.await()
            })
        }
}