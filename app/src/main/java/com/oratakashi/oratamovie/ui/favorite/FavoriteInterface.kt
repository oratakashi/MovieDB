package com.oratakashi.oratamovie.ui.favorite

import com.oratakashi.oratamovie.databinding.AdapterFavoriteBinding
import com.oratakashi.viewbinding.core.ViewHolder

interface FavoriteInterface {
    fun getDetail(id: String, holder : ViewHolder<AdapterFavoriteBinding>)
}