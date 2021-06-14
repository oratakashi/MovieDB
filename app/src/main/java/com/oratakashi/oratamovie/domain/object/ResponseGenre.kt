package com.oratakashi.oratamovie.domain.`object`

import com.oratakashi.oratamovie.domain.model.detail.Genre
import com.oratakashi.oratamovie.domain.model.discover.Discover

data class ResponseGenre(
    val genre : List<Genre>,
    val discover : List<Discover>,
    val total : Int
)
