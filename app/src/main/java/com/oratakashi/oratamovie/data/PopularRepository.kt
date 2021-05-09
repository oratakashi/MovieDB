package com.oratakashi.oratamovie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.oratakashi.oratamovie.data.db.RoomDB
import com.oratakashi.oratamovie.data.model.fav.DataFav
import com.oratakashi.oratamovie.domain.model.cast.Cast
import com.oratakashi.oratamovie.domain.model.detail.Detail
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.oratamovie.domain.repository.Repository
import com.oratakashi.oratamovie.ui.popular.PopularState
import com.oratakashi.oratamovie.utils.PagingBoundary
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class PopularRepository @Inject constructor(
    private val db: RoomDB,
    private val boundary: PagingBoundary
) : Repository {

    override fun getDiscoverPaging(
        callback: MutableLiveData<PopularState>
    ): LiveData<PagedList<Discover>> {
        boundary.callbackState = callback
        return LivePagedListBuilder(
            db.popular().getAll().map {
                Discover(
                    it.backdrop_path,
                    it.id.toString(),
                    it.overview,
                    it.poster_path,
                    it.title,
                    it.release_date
                )
            },
            10,
        )
            .setBoundaryCallback(boundary)
            .build()
    }

    override fun getDiscoverSearchPaging(
        keyword: String
    ): LiveData<PagedList<Discover>> {
        return LivePagedListBuilder(
            db.popular().search("%$keyword%").map {
                Discover(
                    it.backdrop_path,
                    it.id.toString(),
                    it.overview,
                    it.poster_path,
                    it.title,
                    it.release_date
                )
            },
            10
        ).build()
    }

    override fun clearCachePaging(): Single<Int> {
        return db.popular().deleteAll()
    }

    override fun getDiscover(): Observable<List<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun getDiscoverYear(year: Int): Observable<List<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun getDetail(id : Int): Observable<Detail> {
        throw UnsupportedOperationException()
    }

    override fun getCast(id : Int): Observable<List<Cast>> {
        throw UnsupportedOperationException()
    }

    override fun addFav(data: DataFav): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun getFavById(id: String): Single<List<Favorite>> {
        throw UnsupportedOperationException()
    }

    override fun deleteFav(data: DataFav): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun getFavoritePaging(): LiveData<PagedList<Favorite>> {
        throw UnsupportedOperationException()
    }

    override fun getFavoriteSearchPaging(keyword: String): LiveData<PagedList<Favorite>> {
        throw UnsupportedOperationException()
    }
}