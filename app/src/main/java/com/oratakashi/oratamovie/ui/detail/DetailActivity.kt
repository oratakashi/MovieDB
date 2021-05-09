package com.oratakashi.oratamovie.ui.detail

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.oratakashi.oratamovie.BuildConfig
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.ActivityDetailBinding
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.viewbinding.core.intent
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import com.oratakashi.viewbinding.core.tools.visible
import com.oratakashi.viewbinding.core.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val adapter: CastAdapter by lazy {
        CastAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            setupSystemUI()
            ivBack.onClick { onBackPressed() }

            srDetail.setOnRefreshListener {
                srDetail.isRefreshing = false
                viewModel.getDetail(data.id.toInt())
            }

            rvCast.also {
                it.adapter = adapter
                it.layoutManager =
                    LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            }

            btnSave.onClick {
                viewModel.addFav(data)
            }

            viewModel.isFav.observe(this@DetailActivity){
                if(it.isEmpty()){
                    btnSave.setIconResource(R.drawable.ic_add)
                    btnSave.text = getString(R.string.title_add_to_favorite)
                }else{
                    btnSave.setIconResource(R.drawable.ic_saved)
                    btnSave.text = getString(R.string.title_saved)
                }
            }
            viewModel.state.observe(this@DetailActivity) {
                when (it) {
                    is DetailState.Loading -> {
                        ltLoading.setAnimation(R.raw.loading)
                        ltLoading.playAnimation()
                        ltLoading.visible()
                        srDetail.gone()
                    }
                    is DetailState.Result -> {
                        ltLoading.gone()
                        srDetail.visible()

                        ivBackground.load(BuildConfig.IMAGE_URL + it.data.detail.poster_path) {
                            crossfade(true)
                            placeholder(R.drawable.placeholder_landscape)
                        }
                        tvTitle.text = it.data.detail.title
                        tvDescription.text =
                            it.data.detail.overview ?: getString(R.string.title_unknown)

                        adapter.submitData(it.data.cast)

                        if(it.data.detail.genres == null){
                            tvGenre.text = getString(R.string.title_unknown)
                        }else{
                            val builder = StringBuilder()
                            it.data.detail.genres.forEachIndexed { index, genre ->
                                if(index == 0){
                                    builder.append(genre.name)
                                }else{
                                    builder.append(" \u2022 ${genre.name}")
                                }
                            }
                            tvGenre.text = builder.toString()
                        }
                    }
                    is DetailState.Error -> {
                        ltLoading.setAnimation(R.raw.error)
                        ltLoading.playAnimation()
                        ltLoading.visible()
                        srDetail.gone()

                        it.error.printStackTrace()
                        toast("${it.error.message}")
                    }
                }
            }
            viewModel.getDetail(data.id.toInt())
            viewModel.isFav(data.id)
        }
    }

    @Suppress("DEPRECATION")
    private fun setupSystemUI() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                }
            }
        } else {
            window.setDecorFitsSystemWindows(false)
        }
    }

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()
    private val data: Discover by intent("data")
}