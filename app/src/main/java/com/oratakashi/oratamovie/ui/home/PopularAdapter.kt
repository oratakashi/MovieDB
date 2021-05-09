package com.oratakashi.oratamovie.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oratakashi.oratamovie.BuildConfig
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.AdapterHomeBinding
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.viewBinding
import java.util.*

class PopularAdapter(
    private val onClick: (Discover) -> Unit
) : RecyclerView.Adapter<ViewHolder<AdapterHomeBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterHomeBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterHomeBinding>, position: Int) {
        with(holder.binding){
            ivImage.load(BuildConfig.IMAGE_URL + data[position].poster_path) {
                crossfade(true)
                placeholder(R.drawable.placeholder_portrait)
            }
            root.onClick { onClick.invoke(data[position]) }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitData(data: List<Discover>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data : MutableList<Discover> by lazy {
        ArrayList()
    }
}