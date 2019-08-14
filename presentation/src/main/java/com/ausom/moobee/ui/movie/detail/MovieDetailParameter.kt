package com.ausom.moobee.ui.movie.detail

import android.os.Parcelable
import com.ausom.moobee.model.MovieModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailParameter(val movieModel: MovieModel) : Parcelable