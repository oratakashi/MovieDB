package com.oratakashi.oratamovie.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.oratakashi.oratamovie.databinding.AdapterFavoriteBinding
import com.oratakashi.oratamovie.databinding.FragmentFavoriteBinding
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.ui.detail.DetailActivity
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class FavoriteFragment : Fragment(), FavoriteInterface {

    private val adapter: FavoriteAdapter by lazy {
        FavoriteAdapter(
            parent = this,
            onDetail = { data ->
                startActivity(DetailActivity::class.java) {
                    it.putExtra(
                        "data", Discover(
                            data.backdrop_path,
                            data.id,
                            data.overview,
                            data.poster_path,
                            data.title,
                            data.release_date,
                        )
                    )
                }
            },
            onDelete = { data ->
                viewModel.delete(data)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvFavorite.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(requireContext())
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

            viewModel.data.observe(viewLifecycleOwner, adapter::submitList)
            viewModel.detail.observe(viewLifecycleOwner, adapter::setDetail)
            viewModel.getAll()
        }
    }

    override fun getDetail(id: String, holder: ViewHolder<AdapterFavoriteBinding>) {
        viewModel.getDetail(id, holder)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private val binding: FragmentFavoriteBinding by viewBinding()
    private val viewModel: FavoriteViewModel by viewModels()
}