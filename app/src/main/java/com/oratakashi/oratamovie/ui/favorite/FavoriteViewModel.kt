package com.oratakashi.oratamovie.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.oratakashi.oratamovie.databinding.AdapterFavoriteBinding
import com.oratakashi.oratamovie.di.wire.Detail
import com.oratakashi.oratamovie.di.wire.Fav
import com.oratakashi.oratamovie.domain.`object`.FavoriteDetail
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.oratamovie.domain.usecase.UseCase
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    @Fav private val useCase: UseCase,
    @Detail private val detailuseCase: UseCase,
    private val disposable: CompositeDisposable
) : ViewModel() {
    private val keyword: MutableLiveData<String> by liveData()

    val detail: MutableLiveData<FavoriteDetail> by liveData()

    val data: LiveData<PagedList<Favorite>> by lazy {
        Transformations.switchMap(keyword) {
            if (it == null || it.isEmpty()) {
                useCase.getFavoritePaging()
            } else {
                useCase.getFavoriteSearchPaging(it)
            }
        }
    }

    fun getDetail(id: String, holder: ViewHolder<AdapterFavoriteBinding>) {
        detailuseCase.getDetail(id.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { it.printStackTrace() }
            .subscribe {
                detail.postValue(
                    FavoriteDetail(it, holder)
                )
            }
            .let(disposable::add)
    }

    fun getAll() {
        keyword.postValue("")
    }

    fun search(keyword: String) {
        this.keyword.postValue(keyword)
    }

    fun delete(data: Favorite) {
        useCase.deleteFav(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe()
            .let(disposable::add)
    }
}