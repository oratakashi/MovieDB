package com.oratakashi.oratamovie.ui.popular

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.oratakashi.oratamovie.BuildConfig
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.AdapterPopulerBinding
import com.oratakashi.oratamovie.domain.`object`.PopularDetail
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.visible
import com.oratakashi.viewbinding.core.viewBinding

class PopularAdapter(
    private val parent: PopularInterface,
    private val onClick: (Discover) -> Unit
) : PagedListAdapter<Discover, ViewHolder<AdapterPopulerBinding>>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterPopulerBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterPopulerBinding>, position: Int) {
        with(holder.binding) {
            parent.getDetail(getItem(position)?.id!!, holder)
            tvTitle.text = getItem(position)?.title
            ivImage.load(BuildConfig.IMAGE_URL + getItem(position)?.poster_path) {
                crossfade(true)
                placeholder(R.drawable.placeholder_portrait)
            }
            tvSubTitle.text = root.context.getString(R.string.title_loading)
            root.onClick { onClick.invoke(getItem(position)!!) }
        }
    }

    fun setDetail(data: PopularDetail) {
        with(data.holder.binding) {
            if (data.data.cast.isNotEmpty()) {
                val builder = StringBuilder()
                if (data.data.cast.size > 3) {
                    for (i in 0 until 2) {
                        if (i == 2) {
                            builder.append("${data.data.cast[i].name}")
                        } else {
                            builder.append("${data.data.cast[i].name}, ")
                        }
                    }
                    tvSubTitle.text = builder.toString()
                } else {
                    data.data.cast.forEachIndexed { index, cast ->
                        if (index == 2) {
                            builder.append("${cast.name}")
                        } else {
                            builder.append("${cast.name}, ")
                        }
                    }
                    tvSubTitle.text = builder.toString()
                }
            } else {
                tvSubTitle.text = "-"
            }

            if(data.data.detail.genres != null)
            if(data.data.detail.genres.isNotEmpty()){
                tvGenre.visible()
                val builder = StringBuilder()
                if(data.data.detail.genres.size > 2){
                    for (i in 0 until 2) {
                        if (i == 1) {
                            builder.append(data.data.detail.genres[i].name)
                        } else {
                            builder.append("${data.data.detail.genres[i].name}, ")
                        }
                    }
                    tvGenre.text = builder.toString()
                }else{
                    data.data.detail.genres.forEachIndexed { index, genre ->
                        if (index == 1) {
                            builder.append(genre.name)
                        } else {
                            builder.append("${genre.name}, ")
                        }
                    }
                    tvGenre.text = builder.toString()
                }
            }else{
                tvGenre.gone()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Discover>() {
            override fun areItemsTheSame(oldItem: Discover, newItem: Discover): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Discover, newItem: Discover): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}