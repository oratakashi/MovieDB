package com.oratakashi.oratamovie.domain.model.fav

data class Favorite(
    val id : String,
    val backdrop_path : String?,
    val overview : String?,
    val poster_path : String?,
    val title : String?,
    val release_date : String?
)
