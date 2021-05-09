package com.oratakashi.oratamovie.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oratakashi.oratamovie.data.model.discover.DataDiscover
import com.oratakashi.oratamovie.domain.model.discover.Discover

class SliderAdapter(
    private val fragment: Fragment,
    private val data : List<Discover>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return SliderFragment(data[position])
    }
}