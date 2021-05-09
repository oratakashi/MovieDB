package com.oratakashi.oratamovie.domain.interactor

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.oratamovie.data.model.fav.DataFav
import com.oratakashi.oratamovie.di.wire.Home
import com.oratakashi.oratamovie.domain.`object`.ResponseDetail
import com.oratakashi.oratamovie.domain.`object`.ResponseHome
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.oratamovie.domain.repository.Repository
import com.oratakashi.oratamovie.domain.usecase.UseCase
import com.oratakashi.oratamovie.ui.popular.PopularState
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    @Home private val repository: Repository
) : UseCase {
    override fun getHome(year: Int): Observable<ResponseHome> {
        return Observable.zip(
            repository.getDiscover().subscribeOn(Schedulers.io()).map {
                it.subList(0, 5)
            },
            repository.getDiscover().subscribeOn(Schedulers.io()),
            repository.getDiscoverYear(year).subscribeOn(Schedulers.io()),
            { banner, popular, comingsoon ->
                ResponseHome(banner, popular, comingsoon)
            }
        )
    }

    override fun addFav(data: Favorite): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun getFavById(id: String): Single<List<Favorite>> {
        throw UnsupportedOperationException()
    }

    override fun deleteFav(data: Favorite): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun getPopular(callback: MutableLiveData<PopularState>): LiveData<PagedList<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun searchPopular(keyword: String): LiveData<PagedList<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun clearCachePaging(): Single<Int> {
        throw UnsupportedOperationException()
    }

    override fun getDetail(id: Int): Observable<ResponseDetail> {
        throw UnsupportedOperationException()
    }

    override fun getFavoritePaging(): LiveData<PagedList<Favorite>> {
        throw UnsupportedOperationException()
    }

    override fun getFavoriteSearchPaging(keyword: String): LiveData<PagedList<Favorite>> {
        throw UnsupportedOperationException()
    }
}