package com.oratakashi.oratamovie.data.model.cast

import com.google.gson.annotations.SerializedName
import com.oratakashi.oratamovie.data.model.cast.DataCast

data class ResponseCast(
    @SerializedName("cast") val cast : List<DataCast>?,
)
