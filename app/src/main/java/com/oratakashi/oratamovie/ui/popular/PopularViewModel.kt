package com.oratakashi.oratamovie.ui.popular

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.oratakashi.oratamovie.databinding.AdapterPopulerBinding
import com.oratakashi.oratamovie.di.wire.Detail
import com.oratakashi.oratamovie.di.wire.Popular
import com.oratakashi.oratamovie.domain.`object`.PopularDetail
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.domain.usecase.UseCase
import com.oratakashi.viewbinding.core.ViewHolder
import com.oratakashi.viewbinding.core.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    @Popular private val useCase: UseCase,
    @Detail private val detailuseCase: UseCase,
    private val disposable: CompositeDisposable
) : ViewModel() {
    val state: MutableLiveData<PopularState> by liveData()

    val detail: MutableLiveData<PopularDetail> by liveData()

    private val keyword: MutableLiveData<String> by liveData()

    val data: LiveData<PagedList<Discover>> by lazy {
        Transformations.switchMap(keyword) {
            if (it == null || it.isEmpty()) {
                useCase.getPopular(state)
            } else {
                useCase.searchPopular(keyword.value!!)
            }
        }
    }

    fun getDetail(id: String, holder : ViewHolder<AdapterPopulerBinding>){
        detailuseCase.getDetail(id.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { it.printStackTrace() }
            .subscribe {
                detail.postValue(
                    PopularDetail(it, holder)
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

    fun refreshData() {
        useCase.clearCachePaging()
            .map { true }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe {
                if(it) keyword.postValue("")
            }
            .let(disposable::add)
    }
}