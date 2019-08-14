package com.ausom.moobee.di.application.module

import com.ausom.moobee.di.activity.module.ActivityModule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [
    GoogleServiceModule::class,
    DataModule::class,
    ActivityModule::class ])
class ApplicationModule{
    @Provides
    @Singleton
    fun getGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun getBgDispatcher(): CoroutineDispatcher = Dispatchers.Default
}