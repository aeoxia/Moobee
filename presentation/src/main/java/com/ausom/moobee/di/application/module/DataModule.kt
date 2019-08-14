package com.ausom.moobee.di.application.module

import com.ausom.data.entity.mapper.MovieEntityMapper
import com.ausom.data.net.GoogleApiService
import com.ausom.data.net.GoogleDataSource
import com.ausom.data.net.GoogleDataSourceImpl
import com.ausom.data.repository.RepositoryImpl
import com.ausom.domain.repository.Repository
import com.ausom.moobee.di.application.VideoApiBaseUrl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun getMovieMapper(@VideoApiBaseUrl url: String): MovieEntityMapper {
        return MovieEntityMapper(url)
    }

    @Provides
    @Singleton
    fun getRepository(dataSource: GoogleDataSource, movieMapper: MovieEntityMapper): Repository {
        return RepositoryImpl(dataSource,movieMapper)
    }

    @Provides
    @Singleton
    fun getGoogleDataSource(service: GoogleApiService): GoogleDataSource{
        return GoogleDataSourceImpl(service)
    }
}