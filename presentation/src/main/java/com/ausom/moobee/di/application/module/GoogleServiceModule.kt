package com.ausom.moobee.di.application.module

import com.ausom.data.custom.CoroutineCallAdapterFactory
import com.ausom.data.net.GoogleApiService
import com.ausom.moobee.di.application.VideoApiBaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
class GoogleServiceModule{

    @Provides
    @Singleton
    fun getRetrofit(httpClient: OkHttpClient, @VideoApiBaseUrl url: String):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(url)
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getService(retrofit: Retrofit): GoogleApiService {
        return retrofit.create(GoogleApiService::class.java)
    }
}