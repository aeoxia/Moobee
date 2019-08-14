package com.ausom.moobee.di.activity.module

import com.ausom.moobee.MainActivity
import com.ausom.moobee.di.activity.ActivityScope
import com.ausom.moobee.di.fragment.FragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}