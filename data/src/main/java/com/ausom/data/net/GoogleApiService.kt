package com.ausom.data.net

import com.ausom.data.entity.reponse.MovieResponseEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

//https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/videos-enhanced-c.json
interface GoogleApiService {
    @GET("videos-enhanced-c.json")
    fun getMovies(): Deferred<MovieResponseEntity>
}