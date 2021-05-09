package com.oratakashi.oratamovie.data.model.detail

import com.google.gson.annotations.SerializedName

data class ResponseDetail(
    @SerializedName("id") val id : String?,
    @SerializedName("backdrop_path") val backdrop_path : String?,
    @SerializedName("poster_path") val poster_path : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("overview") val overview : String?,
    @SerializedName("release_date") val release_date : String?,
    @SerializedName("vote_average") val vote_average : Float?,
    @SerializedName("genres") val genres : List<DataGenre>,
)
