package com.oratakashi.oratamovie.ui.favorite

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.RoundedCornersTransformation
import com.oratakashi.oratamovie.BuildConfig
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.AdapterFavoriteBinding
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.viewBinding

class FavoriteAdapter(
    private val onDetail : (Favorite)   -> Unit,
    private val onDelete : (Favorite)   -> Unit
) :
    PagedListAdapter<Favorite, ViewHolder<AdapterFavoriteBinding>>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterFavoriteBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterFavoriteBinding>, position: Int) {
        with(holder.binding) {
            ivImage.load(BuildConfig.IMAGE_URL + getItem(position)?.poster_path) {
                transformations(RoundedCornersTransformation(20f))
                crossfade(true)
                placeholder(R.drawable.placeholder_portrait)
            }
            tvTitle.text = getItem(position)?.title
            tvReleaseDate.text =
                if (getItem(position)?.release_date != null || getItem(position)?.release_date!!.isNotEmpty()) {
                    getItem(position)?.release_date!!.split("-")[0]
                } else {
                    "-"
                }
            tvGenre.text = root.context.getString(R.string.title_loading)

            root.onClick { onDetail.invoke(getItem(position)!!) }
            ivFav.onClick { onDelete.invoke(getItem(position)!!) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Favorite>() {
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}