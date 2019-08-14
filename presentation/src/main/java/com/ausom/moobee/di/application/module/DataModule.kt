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