package com.oratakashi.oratamovie.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.oratamovie.data.model.fav.DataFav
import com.oratakashi.oratamovie.domain.`object`.ResponseDetail
import com.oratakashi.oratamovie.domain.`object`.ResponseHome
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.oratamovie.ui.popular.PopularState
import io.reactivex.Observable
import io.reactivex.Single

interface UseCase {
    fun getHome(year: Int): Observable<ResponseHome>
    fun getDetail(id: Int): Observable<ResponseDetail>
    fun getPopular(
        callback: MutableLiveData<PopularState>
    ): LiveData<PagedList<Discover>>

    fun searchPopular(
        keyword: String
    ): LiveData<PagedList<Discover>>

    fun clearCachePaging(): Single<Int>

    fun addFav(data: Favorite): Single<Boolean>
    fun getFavById(id: String): Single<List<Favorite>>
    fun deleteFav(data: Favorite) : Single<Boolean>
    fun getFavoritePaging() : LiveData<PagedList<Favorite>>
    fun getFavoriteSearchPaging(
        keyword: String
    ): LiveData<PagedList<Favorite>>
}