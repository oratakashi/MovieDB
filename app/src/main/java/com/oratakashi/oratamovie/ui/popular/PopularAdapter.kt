package com.oratakashi.oratamovie.ui.popular

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.RoundedCornersTransformation
import com.oratakashi.oratamovie.BuildConfig
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.AdapterPopulerBinding
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.viewBinding

class PopularAdapter(
    private val onClick: (Discover) -> Unit
) : PagedListAdapter<Discover, ViewHolder<AdapterPopulerBinding>>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterPopulerBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterPopulerBinding>, position: Int) {
        with(holder.binding) {
            tvTitle.text = getItem(position)?.title
            ivImage.load(BuildConfig.IMAGE_URL + getItem(position)?.poster_path) {
                crossfade(true)
                placeholder(R.drawable.placeholder_portrait)
            }
            tvSubTitle.text = root.context.getString(R.string.title_loading)
            root.onClick { onClick.invoke(getItem(position)!!) }
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