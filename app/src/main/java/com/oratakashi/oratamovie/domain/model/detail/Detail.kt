package com.oratakashi.oratamovie.domain.model.detail

data class Detail(
    val id : String?,
    val backdrop_path : String?,
    val poster_path : String?,
    val title : String?,
    val overview : String?,
    val release_date : String?,
    val vote_average : Float?,
    val genres : List<Genre>?,
)
