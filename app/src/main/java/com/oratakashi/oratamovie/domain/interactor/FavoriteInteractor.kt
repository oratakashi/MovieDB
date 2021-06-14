package com.oratakashi.oratamovie.domain.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.oratakashi.oratamovie.data.model.fav.DataFav
import com.oratakashi.oratamovie.di.wire.Fav
import com.oratakashi.oratamovie.domain.`object`.ResponseDetail
import com.oratakashi.oratamovie.domain.`object`.ResponseGenre
import com.oratakashi.oratamovie.domain.`object`.ResponseHome
import com.oratakashi.oratamovie.domain.model.discover.Discover
import com.oratakashi.oratamovie.domain.model.fav.Favorite
import com.oratakashi.oratamovie.domain.repository.Repository
import com.oratakashi.oratamovie.domain.usecase.UseCase
import com.oratakashi.oratamovie.ui.popular.PopularState
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(
    @Fav private val repository: Repository
) : UseCase {

    override fun deleteFav(data: Favorite): Single<Boolean> {
        return repository.deleteFav(
            DataFav(
                data.id,
                data.backdrop_path,
                data.overview,
                data.poster_path,
                data.title,
                data.release_date,
            )
        )
    }

    override fun getFavoritePaging(): LiveData<PagedList<Favorite>> {
        return repository.getFavoritePaging()
    }

    override fun getFavoriteSearchPaging(keyword: String): LiveData<PagedList<Favorite>> {
        return repository.getFavoriteSearchPaging(keyword)
    }

    override fun addFav(data: Favorite): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun getHome(year: Int): Observable<ResponseHome> {
        throw UnsupportedOperationException()
    }

    override fun getDetail(id: Int): Observable<ResponseDetail> {
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

    override fun getFavById(id: String): Single<List<Favorite>> {
        throw UnsupportedOperationException()
    }

    override fun getGenre(): Observable<ResponseGenre> {
        throw UnsupportedOperationException()
    }
}