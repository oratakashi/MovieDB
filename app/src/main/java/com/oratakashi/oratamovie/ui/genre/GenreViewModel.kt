package com.oratakashi.oratamovie.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.oratamovie.di.wire.Genre
import com.oratakashi.oratamovie.domain.usecase.UseCase
import com.oratakashi.viewbinding.core.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    @Genre private val useCase: UseCase,
    private val disposable: CompositeDisposable
) : ViewModel() {
    val state: MutableLiveData<GenreState> by liveData()

    fun getGenre() {
        useCase.getGenre()
            .map<GenreState>(GenreState::Result)
            .onErrorReturn(GenreState::Error)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(GenreState.Loading)
            .subscribe(state::postValue)
            .let { return@let disposable::add }

    }
}