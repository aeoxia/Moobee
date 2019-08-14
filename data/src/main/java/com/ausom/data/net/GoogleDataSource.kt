package com.ausom.data.net

import com.ausom.data.entity.reponse.MovieResponseEntity
import com.ausom.domain.common.ResultWrapper
import kotlinx.coroutines.channels.ReceiveChannel

interface GoogleDataSource {
    suspend fun getMovies(): ReceiveChannel<ResultWrapper<Exception, MovieResponseEntity>>
}