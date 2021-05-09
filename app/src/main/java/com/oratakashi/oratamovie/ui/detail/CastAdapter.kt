package com.oratakashi.oratamovie.ui.detail

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.oratakashi.oratamovie.BuildConfig
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.AdapterCastBinding
import com.oratakashi.oratamovie.domain.model.cast.Cast
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.viewBinding
import com.squareup.picasso.Picasso
import java.util.*

class CastAdapter : RecyclerView.Adapter<ViewHolder<AdapterCastBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterCastBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterCastBinding>, position: Int) {
        with(holder.binding) {
            Log.e("debug", "debug: ${data[position]}")
            Picasso.get()
                .load(BuildConfig.IMAGE_URL + data[position].profile_path)
                .placeholder(R.drawable.placeholder_portrait)
                .error(R.drawable.placeholder_portrait)
                .into(ivImage)
            tvName.text = data[position].name
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitData(data: List<Cast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<Cast> by lazy {
        ArrayList()
    }
}