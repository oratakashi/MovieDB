package com.oratakashi.oratamovie.domain.`object`

import com.oratakashi.oratamovie.databinding.AdapterFavoriteBinding
import com.oratakashi.viewbinding.core.ViewHolder

data class FavoriteDetail(
    val data: ResponseDetail,
    val holder: ViewHolder<AdapterFavoriteBinding>
)
