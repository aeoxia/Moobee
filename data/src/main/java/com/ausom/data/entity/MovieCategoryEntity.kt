package com.ausom.data.entity

import com.google.gson.annotations.SerializedName

data class MovieCategoryEntity(val name: String, @SerializedName("videos") val movies: List<MovieEntity>)