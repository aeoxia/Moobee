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