package com.ausom.moobee.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.ausom.moobee.MoobeeApplication
import com.ausom.moobee.di.application.DaggerMoobeeAppComponent
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

object AppInjector{
    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    fun init(application: MoobeeApplication){
        DaggerMoobeeAppComponent.builder().application(application)
            .build().inject(application)

        application.registerActivityLifecycleCallbacks(object :Application.ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity?) {}
            override fun onActivityResumed(activity: Activity?) {}
            override fun onActivityStarted(activity: Activity?) {}
            override fun onActivityDestroyed(activity: Activity?) {}
            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}
            override fun onActivityStopped(activity: Activity?) {}
            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }
        })
    }

    private fun handleActivity(activity: Activity?) {
        if(activity is HasSupportFragmentInjector) AndroidInjection.inject(activity)

        if(activity is FragmentActivity){
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks(){
                    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
                        super.onFragmentAttached(fm, f, context)
                        if(f is Injectable) AndroidSupportInjection.inject(f)
                    }
                },true)
        }
    }
}