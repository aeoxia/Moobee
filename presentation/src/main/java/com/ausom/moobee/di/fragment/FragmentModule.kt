package com.ausom.moobee.di.fragment

import com.ausom.moobee.ui.movie.detail.MovieDetailFragment
import com.ausom.moobee.ui.movie.detail.MovieDetailModule
import com.ausom.moobee.ui.movie.list.MovieListFragment
import com.ausom.moobee.ui.movie.list.MovieListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MovieDetailModule::class])
    abstract fun contributesMovieDetailFragment(): MovieDetailFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [MovieListModule::class])
    abstract fun contributesMovieListFragment(): MovieListFragment
}