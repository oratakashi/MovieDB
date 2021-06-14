package com.oratakashi.oratamovie.data.model.genre

import com.google.gson.annotations.SerializedName
import com.oratakashi.oratamovie.data.model.genre.DataGenre

data class ResponseGenre(
    @field:SerializedName("genres") val data : List<DataGenre>,
)
