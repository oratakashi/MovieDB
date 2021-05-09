package com.oratakashi.oratamovie.ui.popular

import com.oratakashi.oratamovie.databinding.AdapterPopulerBinding
import com.oratakashi.viewbinding.core.ViewHolder

interface PopularInterface {
    fun getDetail(id: String, holder : ViewHolder<AdapterPopulerBinding>)
}