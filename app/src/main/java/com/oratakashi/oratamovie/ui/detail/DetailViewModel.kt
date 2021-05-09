package com.oratakashi.oratamovie.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.oratamovie.di.wire.Detail
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.oratamovie.domain.usecase.UseCase
import com.oratakashi.viewbinding.core.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Detail private val useCase: UseCase,
    private val disposable: CompositeDisposable
) : ViewModel() {
    val state: MutableLiveData<DetailState> by liveData()
    val isFav: MutableLiveData<List<Favorite>> by liveData()

    fun getDetail(id: Int) {
        useCase.getDetail(id)
            .map<DetailState>(DetailState::Result)
            .onErrorReturn(DetailState::Error)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(DetailState.Loading)
            .subscribe(state::postValue)
            .let(disposable::add)
    }

    fun isFav(id: String) {
        useCase.getFavById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe(isFav::postValue)
            .let(disposable::add)
    }

    fun addFav(data: Discover) {
        useCase.getFavById(data.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                if (it.isEmpty()) {
                    useCase.addFav(
                        Favorite(
                            data.id,
                            data.backdrop_path,
                            data.overview,
                            data.poster_path,
                            data.title,
                            data.release_date,
                        )
                    ).subscribeOn(Schedulers.io())
                } else {
                    useCase.deleteFav(
                        Favorite(
                            data.id,
                            data.backdrop_path,
                            data.overview,
                            data.poster_path,
                            data.title,
                            data.release_date,
                        )
                    ).subscribeOn(Schedulers.io())
                }
            }
            .flatMap {
                useCase.getFavById(data.id).subscribeOn(Schedulers.io())
            }
            .toFlowable()
            .subscribe(isFav::postValue)
            .let(disposable::add)
    }
}