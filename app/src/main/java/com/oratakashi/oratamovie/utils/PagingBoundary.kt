package com.oratakashi.oratamovie.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.oratamovie.data.db.RoomDB
import com.oratakashi.oratamovie.data.model.discover.DataDiscover
import com.oratakashi.oratamovie.data.network.PopularEndpoint
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.ui.popular.PopularState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PagingBoundary @Inject constructor(
    private val endpoint: PopularEndpoint,
    private val db: RoomDB,
    private val disposable: CompositeDisposable
) : PagedList.BoundaryCallback<Discover>() {

    lateinit var callbackState: MutableLiveData<PopularState>
    var page = 1

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Log.e("debug", "debug: Empty Data $page")
        page = 1
        endpoint.getDiscover(page++)
            .map<PopularState> {
                PopularState.Result(it.data!!.map { data ->
                    Discover(
                        data.backdrop_path,
                        data.id.toString(),
                        data.overview,
                        data.poster_path,
                        data.title,
                        data.release_date
                    )
                })
            }
            .onErrorReturn(PopularState::Error)
            .toFlowable()
            .startWith(PopularState.Loading)
            .subscribe{
                when(it){
                    is PopularState.Loading -> callbackState::postValue
                    is PopularState.Result  -> {
                        db.popular().addAll(it.data.map { data ->
                            DataDiscover(
                                data.backdrop_path,
                                data.id.toInt(),
                                data.overview,
                                data.poster_path,
                                data.title,
                                data.release_date,
                                Generate.getTimeMilis()
                            )
                        })
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                            .let { return@let disposable::add }
                        callbackState.postValue(it)
                    }
                    is PopularState.Error   -> callbackState::postValue
                }
            }
            .let { return@let disposable::add }
    }

    override fun onItemAtFrontLoaded(itemAtFront: Discover) {
        super.onItemAtFrontLoaded(itemAtFront)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Discover) {
        super.onItemAtEndLoaded(itemAtEnd)
        endpoint.getDiscover(page++)
            .map<PopularState> {
                PopularState.Result(it.data!!.map { data ->
                    Discover(
                        data.backdrop_path,
                        data.id.toString(),
                        data.overview,
                        data.poster_path,
                        data.title,
                        data.release_date,
                    )
                })
            }
            .onErrorReturn(PopularState::Error)
            .toFlowable()
            .subscribe{
                when(it){
                    is PopularState.Loading -> callbackState::postValue
                    is PopularState.Result  -> {
                        db.popular().addAll(it.data.map { data ->
                            DataDiscover(
                                data.backdrop_path,
                                data.id.toInt(),
                                data.overview,
                                data.poster_path,
                                data.title,
                                data.release_date,
                                Generate.getTimeMilis()
                            )
                        })
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                            .let { return@let disposable::add }
                        callbackState.postValue(it)
                    }
                    is PopularState.Error   -> callbackState::postValue
                }
            }
            .let { return@let disposable::add }
    }
}