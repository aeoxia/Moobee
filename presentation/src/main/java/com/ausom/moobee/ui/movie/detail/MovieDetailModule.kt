package com.ausom.moobee.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.ausom.moobee.di.application.ViewModelFactory
import com.ausom.moobee.di.application.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object MovieDetailModule {

    @JvmStatic
    @Provides
    @IntoMap
    @ViewModelKey(MovieDetailViewModelImpl::class)
    fun provideMovieDetailViewModelIntoMap(viewModel: MovieDetailViewModelImpl): ViewModel =
        viewModel

    @JvmStatic
    @Provides
    fun provideMovieDetailViewModel(
        fragment: MovieDetailFragment,
        viewModelFactory: ViewModelFactory
    ): MovieDetailViewModel =
        ViewModelProviders.of(fragment, viewModelFactory)[MovieDetailViewModelImpl::class.java]

    @JvmStatic
    @Provides
    fun provideMovieDetailParameter(fragment: MovieDetailFragment): MovieDetailParameter =
        MovieDetailFragmentArgs.fromBundle(fragment.arguments!!).movieDetailParameter

}