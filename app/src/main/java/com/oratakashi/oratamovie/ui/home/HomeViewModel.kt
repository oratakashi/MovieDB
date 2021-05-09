package com.oratakashi.oratamovie.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.liveData
import com.oratakashi.oratamovie.di.wire.Home
import com.oratakashi.oratamovie.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Home private val useCase: UseCase,
    private val disposable: CompositeDisposable
) : ViewModel() {

    val state : MutableLiveData<HomeState> by liveData()

    fun getHome(year: Int){
        useCase.getHome(year)
            .map<HomeState>(HomeState::Result)
            .onErrorReturn(HomeState::Error)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(HomeState.Loading)
            .subscribe(state::postValue)
            .let(disposable::add)
    }
}