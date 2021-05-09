package com.oratakashi.oratamovie.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.FragmentPopularBinding
import com.oratakashi.oratamovie.ui.detail.DetailActivity
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.visible
import com.oratakashi.viewbinding.core.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private val adapter: PopularAdapter by lazy {
        PopularAdapter { data ->
            startActivity(DetailActivity::class.java) {
                it.putExtra("data", data)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvPopular.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(requireContext(), 2)
            }
            srPopular.setOnRefreshListener {
                srPopular.isRefreshing = false
                viewModel.refreshData()
            }

            etSearch.textChangeEvents()
                .skipInitialValue()
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TextViewTextChangeEvent>() {
                    override fun onNext(t: TextViewTextChangeEvent) {
                        val keyword = t.text.trim().toString()
                        if (keyword.isNotBlank() && keyword.isNotEmpty() && keyword.length >= 3) {
                            viewModel.search(keyword)
                        } else {
                            viewModel.getAll()
                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })

            viewModel.getAll()
            viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is PopularState.Loading -> {
                        ltLoading.setAnimation(R.raw.loading)
                        ltLoading.playAnimation()
                        ltLoading.visible()
                        srPopular.gone()
                        tvSearchResult.gone()
                    }
                    is PopularState.Result -> {
                        ltLoading.gone()
                        srPopular.visible()
                        tvSearchResult.gone()
                    }
                    is PopularState.Error -> {
                        ltLoading.setAnimation(R.raw.error)
                        ltLoading.playAnimation()
                        ltLoading.visible()
                        srPopular.gone()
                        tvSearchResult.gone()
                    }
                }
            }
            viewModel.data.observe(viewLifecycleOwner, adapter::submitList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @Inject
    lateinit var disposable: CompositeDisposable

    private val binding: FragmentPopularBinding by viewBinding()
    private val viewModel: PopularViewModel by viewModels()
}