package com.oratakashi.oratamovie.domain.`object`

import com.oratakashi.oratamovie.domain.model.discover.Discover

data class ResponseHome(
    val banner : List<Discover>,
    val popular : List<Discover>,
    val comingsoon : List<Discover>
)
