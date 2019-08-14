package com.ausom.moobee.di.application

import android.app.Application
import com.ausom.moobee.MoobeeApplication
import com.ausom.moobee.di.application.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class
])
interface MoobeeAppComponent{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): MoobeeAppComponent
    }

    fun inject(app: MoobeeApplication)
}