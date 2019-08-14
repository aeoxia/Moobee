package com.ausom.moobee.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class MovieModel(
        val id: String = UUID.randomUUID().toString(),
        val title: String,
        val subtitle: String,
        val source: String,
        val thumb: String,
        val poster: String
) : Parcelable