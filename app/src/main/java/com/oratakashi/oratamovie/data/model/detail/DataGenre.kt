package com.oratakashi.oratamovie.data.model.detail

import com.google.gson.annotations.SerializedName

data class DataGenre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)