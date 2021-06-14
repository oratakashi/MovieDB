package com.oratakashi.oratamovie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.oratamovie.data.db.RoomDB
import com.oratakashi.oratamovie.data.model.fav.DataFav
import com.oratakashi.oratamovie.data.network.DetailEndpoint
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

class DetailRepository @Inject constructor(
    private val endpoint: DetailEndpoint,
    private val db : RoomDB
) : Repository {
    override fun getDetail(id: Int): Observable<Detail> {
        return endpoint.getDetail(id).map {
            Detail(
                it.id,
                it.backdrop_path,
                it.poster_path,
                it.title,
                it.overview,
                it.release_date,
                it.vote_average,
                it.genres.map { genre ->
                    Genre(
                        genre.id,
                        genre.name
                    )
                },
            )
        }
    }

    override fun getCast(id: Int): Observable<List<Cast>> {
        return endpoint.getCast(id).map {
            it.cast?.map { cast ->
                Cast(
                    cast.name,
                    cast.character,
                    cast.profile_path
                )
            }
        }
    }

    override fun addFav(data: DataFav): Single<Boolean> {
        return db.fav().add(data).map { true }
    }

    override fun getFavById(id: String): Single<List<Favorite>> {
        return db.fav().getById(id).map { it.map { data ->
            Favorite(
                data.id,
                data.backdrop_path,
                data.overview,
                data.id,
                data.id,
                data.id,
            )
        } }
    }

    override fun deleteFav(data: DataFav): Single<Boolean> {
        return db.fav().delete(data).map { true }
    }

    override fun getDiscover(): Observable<List<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun getDiscoverYear(year: Int): Observable<List<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun getDiscoverPaging(callback: MutableLiveData<PopularState>): LiveData<PagedList<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun getDiscoverSearchPaging(keyword: String): LiveData<PagedList<Discover>> {
        throw UnsupportedOperationException()
    }

    override fun clearCachePaging(): Single<Int> {
        throw UnsupportedOperationException()
    }

    override fun getFavoritePaging(): LiveData<PagedList<Favorite>> {
        throw UnsupportedOperationException()
    }

    override fun getFavoriteSearchPaging(keyword: String): LiveData<PagedList<Favorite>> {
        throw UnsupportedOperationException()
    }

    override fun getGenre(): Observable<List<Genre>> {
        throw UnsupportedOperationException()
    }

    override fun getDiscoverDetail(): Observable<DiscoverDetail> {
        throw UnsupportedOperationException()
    }
}