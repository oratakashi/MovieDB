package com.oratakashi.oratamovie.domain.model.discover

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Discover(
    val backdrop_path : String?,
    val id : String,
    val overview : String?,
    val poster_path : String?,
    val title : String?,
    val release_date : String?
) : Parcelable
