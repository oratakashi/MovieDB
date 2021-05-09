package com.oratakashi.oratamovie.domain.`object`

import com.oratakashi.oratamovie.databinding.AdapterPopulerBinding
import com.oratakashi.viewbinding.core.ViewHolder

data class PopularDetail(
    val data : ResponseDetail,
    val holder : ViewHolder<AdapterPopulerBinding>
)
