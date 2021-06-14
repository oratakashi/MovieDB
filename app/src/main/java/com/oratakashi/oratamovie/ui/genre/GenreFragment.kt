package com.oratakashi.oratamovie.ui.genre

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.FragmentGenreBinding
import com.oratakashi.oratamovie.domain.`object`.Pagination
import com.oratakashi.viewbinding.core.tools.*
import com.oratakashi.viewbinding.core.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : Fragment() {

    private val discover : DiscoverAdapter by lazy {
        DiscoverAdapter()
    }

    private val paging : PaginationAdapter by lazy {
        PaginationAdapter{ data, total ->
            paging.moveToPosition(data)
            with(binding){
                rvPagination.scrollToPosition(0)
                when (data.number) {
                    total -> {
                        ivNext.invisible()
                    }
                    1 -> {
                        ivBack.invisible()
                    }
                    else -> {
                        ivNext.visible()
                        ivBack.visible()
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            rvDiscover.also {
                it.adapter = discover
                it.layoutManager = LinearLayoutManager(requireContext())
                it.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            }

            rvPagination.also {
                it.adapter = paging
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }

            ivNext.onClick { paging.moveToNext() }
            ivBack.onClick { paging.moveToBack() }

            viewModel.state.observe(viewLifecycleOwner){
                when(it){
                    is GenreState.Loading   -> {
                        ltLoading.setAnimation(R.raw.loading)
                        ltLoading.playAnimation()
                        ltLoading.visible()
                        rvGenre.gone()
                        rvDiscover.gone()
                    }
                    is GenreState.Result    -> {
                        ltLoading.gone()
                        rvGenre.visible()
                        rvDiscover.visible()
                        discover.submitData(it.data.discover)
                        val data : MutableList<Pagination> = ArrayList()

                        for(i in 0 until when(it.data.total > 9){
                            true    -> 9
                            false   -> it.data.total
                        }){
                            data.add(
                                Pagination(i+1, "${i + 1}", i == 0)
                            )
                        }

                        paging.submitData(data)
                        paging.submitTotal(it.data.total)
                        ivBack.invisible()
                    }
                    is GenreState.Error     -> {
                        ltLoading.setAnimation(R.raw.error)
                        ltLoading.playAnimation()
                        ltLoading.visible()
                        rvGenre.gone()
                        rvDiscover.gone()
                        it.error.printStackTrace()
                        toast("${it.error.message}")
                    }
                }
            }
            viewModel.getGenre()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private val binding : FragmentGenreBinding by viewBinding()
    private val viewModel : GenreViewModel by viewModels()
}