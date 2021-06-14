package com.oratakashi.oratamovie.ui.genre

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.oratamovie.databinding.AdapterTableBinding
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.viewBinding
import java.util.ArrayList

class DiscoverAdapter : RecyclerView.Adapter<ViewHolder<AdapterTableBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<AdapterTableBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<AdapterTableBinding>, position: Int) {
        with(holder.binding){
            tvTitle.text = data[position].title
            tvId.text = data[position].id
            tvReleaseDate.text = data[position].release_date
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitData(data: List<Discover>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<Discover> by lazy {
        ArrayList()
    }
}