package com.oratakashi.oratamovie.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.oratamovie.data.model.fav.DataFav
import com.oratakashi.oratamovie.domain.model.cast.Cast
import com.oratakashi.oratamovie.domain.model.detail.Detail
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.oratamovie.ui.popular.PopularState
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    fun getDiscover(): Observable<List<Discover>>
    fun getDiscoverYear(year: Int): Observable<List<Discover>>
    fun getDetail(id: Int): Observable<Detail>
    fun getCast(id: Int): Observable<List<Cast>>

    fun getDiscoverPaging(
        callback: MutableLiveData<PopularState>
    ): LiveData<PagedList<Discover>>
    fun getDiscoverSearchPaging(
        keyword: String
    ): LiveData<PagedList<Discover>>
    fun getFavoritePaging() : LiveData<PagedList<Favorite>>
    fun getFavoriteSearchPaging(
        keyword: String
    ): LiveData<PagedList<Favorite>>

    fun clearCachePaging(): Single<Int>

    fun addFav(data: DataFav): Single<Boolean>
    fun getFavById(id: String): Single<List<Favorite>>
    fun deleteFav(data: DataFav) : Single<Boolean>
}