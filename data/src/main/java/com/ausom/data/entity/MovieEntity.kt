package com.ausom.data.entity

import com.google.gson.annotations.SerializedName


data class MovieEntity(
        val title: String?,
        val subtitle: String?,
        val sources: List<String>?,
        @SerializedName(value = "image-480x270")
        val thumb: String?,
        @SerializedName(value = "image-780x1200")
        val poster: String?
)