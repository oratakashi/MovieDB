package com.oratakashi.oratamovie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.oratakashi.oratamovie.BuildConfig
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.FragmentSliderBinding
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.ui.detail.DetailActivity
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.viewBinding

class SliderFragment(
    val data: Discover
) : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            root.load(BuildConfig.IMAGE_URL + data.backdrop_path){
                crossfade(true)
                placeholder(R.drawable.placeholder_landscape)
            }
            root.onClick {
                startActivity(DetailActivity::class.java) {
                    it.putExtra("data", data)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    private val binding: FragmentSliderBinding by viewBinding()
}