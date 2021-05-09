package com.oratakashi.oratamovie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.data.model.discover.DataDiscover
import com.oratakashi.oratamovie.databinding.FragmentHomeBinding
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.ui.detail.DetailActivity
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.toast
import com.oratakashi.viewbinding.core.tools.visible
import com.oratakashi.viewbinding.core.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val slider : SliderAdapter by lazy {
        SliderAdapter(this, dataSlider)
    }

    private val popular : PopularAdapter by lazy {
        PopularAdapter { data ->
            startActivity(DetailActivity::class.java){
                it.putExtra("data", data)
            }
        }
    }

    private val comingsoon : ComingsoonAdapter by lazy {
        ComingsoonAdapter { data ->
            startActivity(DetailActivity::class.java){
                it.putExtra("data", data)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            vpSlider.adapter = slider
            TabLayoutMediator(tlHome, vpSlider) { _, _ -> }.attach()

            root.setOnRefreshListener {
                root.isRefreshing = false
                viewModel.getHome(
                    Calendar.getInstance().get(Calendar.YEAR) + 1
                )
            }

            rvPopular.also {
                it.adapter = popular
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

            rvComingSoon.also {
                it.adapter = comingsoon
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

            viewModel.getHome(
                Calendar.getInstance().get(Calendar.YEAR) + 1
            )
            viewModel.state.observe(viewLifecycleOwner){
                when(it){
                    is HomeState.Loading    -> {
                        ltLoading.setAnimation(R.raw.loading)
                        ltLoading.playAnimation()
                        ltLoading.visible()
                        tvTitleComingSoon.gone()
                        tvTitlePopular.gone()
                        rvComingSoon.gone()
                        rvPopular.gone()
                        vpSlider.gone()
                    }
                    is HomeState.Result     -> {
                        ltLoading.gone()
                        tvTitleComingSoon.visible()
                        tvTitlePopular.visible()
                        rvComingSoon.visible()
                        rvPopular.visible()
                        vpSlider.visible()

                        popular.submitData(it.data.popular)
                        comingsoon.submitData(it.data.comingsoon)

                        dataSlider.clear()
                        dataSlider.addAll(it.data.banner)
                        slider.notifyDataSetChanged()
                    }
                    is HomeState.Error      -> {
                        ltLoading.setAnimation(R.raw.error)
                        ltLoading.playAnimation()
                        ltLoading.visible()
                        tvTitleComingSoon.gone()
                        tvTitlePopular.gone()
                        rvComingSoon.gone()
                        rvPopular.gone()
                        vpSlider.gone()
                        it.error.printStackTrace()
                        toast("${it.error.message}")
                    }
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

    private val dataSlider : MutableList<Discover> by lazy {
        ArrayList()
    }

    private val binding : FragmentHomeBinding by viewBinding()
    private val viewModel : HomeViewModel by viewModels()
}