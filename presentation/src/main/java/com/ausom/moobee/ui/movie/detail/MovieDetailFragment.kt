package com.ausom.moobee.ui.movie.detail

import com.ausom.moobee.R
import com.ausom.moobee.databinding.FragmentMovieDetailBinding
import com.ausom.moobee.di.Injectable
import com.ausom.moobee.ui.base.BaseFragment
import javax.inject.Inject

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding,MovieDetailViewModel>(), Injectable {

    @Inject
    override lateinit var viewModel: MovieDetailViewModel
    override val layoutRes = R.layout.fragment_movie_detail

    override fun onBindingCreated(binding: FragmentMovieDetailBinding) {
        binding.viewModel = viewModel
    }
}