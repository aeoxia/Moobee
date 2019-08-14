package com.ausom.moobee

import android.app.Activity
import android.app.Application
import com.ausom.moobee.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MoobeeApplication: Application(), HasActivityInjector{

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}