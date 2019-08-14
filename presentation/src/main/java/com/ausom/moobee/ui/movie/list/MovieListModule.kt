package com.ausom.moobee.ui.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.ausom.moobee.di.application.ViewModelFactory
import com.ausom.moobee.di.application.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object MovieListModule {
    @JvmStatic
    @Provides
    @IntoMap
    @ViewModelKey(MovieListViewModelImpl::class)
    fun provideMovieListViewModelIntoMap(viewModel: MovieListViewModelImpl): ViewModel =
        viewModel

    @JvmStatic
    @Provides
    fun provideMovieListViewModel(
        fragment: MovieListFragment,
        viewModelFactory: ViewModelFactory
    ): MovieListViewModel =
        ViewModelProviders.of(fragment, viewModelFactory)[MovieListViewModelImpl::class.java]

}