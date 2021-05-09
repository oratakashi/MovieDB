package com.oratakashi.oratamovie.ui.popular

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.oratakashi.oratamovie.di.wire.Popular
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    @Popular private val useCase: UseCase,
    private val disposable: CompositeDisposable
) : ViewModel() {
    val state: MutableLiveData<PopularState> by lazy {
        MutableLiveData()
    }

    private val keyword: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val data: LiveData<PagedList<Discover>> by lazy {
        Transformations.switchMap(keyword) {
            if (it == null || it.isEmpty()) {
                useCase.getPopular(state)
            } else {
                useCase.searchPopular(keyword.value!!)
            }
        }
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
            .let { return@let disposable::add }
    }
}