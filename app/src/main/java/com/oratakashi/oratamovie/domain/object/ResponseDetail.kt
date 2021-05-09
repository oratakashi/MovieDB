package com.oratakashi.oratamovie.domain.`object`

import com.oratakashi.oratamovie.domain.model.cast.Cast
import com.oratakashi.oratamovie.domain.model.detail.Detail

data class ResponseDetail(
    val detail: Detail,
    val cast: List<Cast>
)
