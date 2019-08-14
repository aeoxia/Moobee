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

import android.app.Application
import com.ausom.moobee.R
import com.ausom.moobee.di.application.VideoApiBaseUrl
import com.ausom.moobee.internal.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getOkHttpClient(cache: Cache, logging: HttpLoggingInterceptor, requestInterceptor: Interceptor,app: Application):OkHttpClient{
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder
            .addInterceptor(requestInterceptor)
            .addInterceptor(ConnectivityInterceptor(app))
            .cache(cache)
            .interceptors()
            .add(logging)

        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun getRequestInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .build()
            return@Interceptor chain.proceed(request)
        }

    @Provides
    @Singleton
    @VideoApiBaseUrl
    fun getVideoApiBaseUrl(app: Application): String {
        return app.getString(R.string.api_base_url)
    }

    @Provides
    @Singleton
    fun getHttpLoggingInterceptor():HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun getCache(cacheFile: File):Cache{
        return Cache(cacheFile,10*1000*1000) //10mb cache
    }

    @Provides
    @Singleton
    fun getCacheFile(app: Application):File{
        val cacheFile = File(app.cacheDir,"okhttp_cache")
        cacheFile.mkdirs()
        return cacheFile
    }
}