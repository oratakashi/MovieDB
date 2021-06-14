package com.oratakashi.oratamovie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.oratamovie.data.model.fav.DataFav
import com.oratakashi.oratamovie.data.network.GenreEndpoint
import com.oratakashi.oratamovie.domain.model.cast.Cast
import com.oratakashi.oratamovie.domain.model.detail.Detail
import com.oratakashi.oratamovie.domain.model.detail.Genre
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.domain.model.discover.DiscoverDetail
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.oratamovie.domain.repository.Repository
import com.oratakashi.oratamovie.ui.popular.PopularState
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val endpoint: GenreEndpoint
) : Repository {

    override fun getDiscoverDetail(): Observable<DiscoverDetail> {
        return endpoint.getDiscover().map {
            DiscoverDetail(
                it.data?.map { data ->
                    Discover(
                        data.backdrop_path,
                        data.id.toString(),
                        data.overview,
                        data.poster_path,
                        data.title,
                        data.release_date
                    )
                }!!,
                it.total_pages ?: 0
            )
        }
    }

    override fun getGenre(): Observable<List<Genre>> {
        return endpoint.getGenre().map {
            it.data.map { data ->
                Genre(
                    data.id,
                    data.name
                )
            }
        }
    }

    override fun getDiscover(): Observable<List<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun getDiscoverYear(year: Int): Observable<List<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun getDetail(id: Int): Observable<Detail> {
        throw UnsupportedOperationException()
    }

    override fun getCast(id: Int): Observable<List<Cast>> {
        throw UnsupportedOperationException()
    }

    override fun getDiscoverPaging(callback: MutableLiveData<PopularState>): LiveData<PagedList<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun getDiscoverSearchPaging(keyword: String): LiveData<PagedList<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun getFavoritePaging(): LiveData<PagedList<Favorite>> {
        throw UnsupportedOperationException()
    }

    override fun getFavoriteSearchPaging(keyword: String): LiveData<PagedList<Favorite>> {
        throw UnsupportedOperationException()
    }

    override fun clearCachePaging(): Single<Int> {
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
}