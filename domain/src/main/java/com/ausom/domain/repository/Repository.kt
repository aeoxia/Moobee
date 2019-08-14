package com.ausom.domain.repository

import com.ausom.domain.common.ResultWrapper
import com.ausom.domain.model.Movie
import kotlinx.coroutines.channels.ReceiveChannel

interface Repository {
    suspend fun getMovies(): ReceiveChannel<ResultWrapper<Exception,List<Movie>>>
}
