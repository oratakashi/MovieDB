package com.oratakashi.oratamovie.data.network

import com.oratakashi.oratamovie.data.model.genre.ResponseGenre
import com.oratakashi.oratamovie.data.model.discover.ResponseDiscover
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreEndpoint {
    @GET("genre/movie/list")
    fun getGenre(): Observable<ResponseGenre>

    @GET("discover/movie")
    fun getDiscover(
        @Query("page") page: Int = 1,
        @Query("include_adult") include_adult: Boolean = false,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Observable<ResponseDiscover>

    @GET("discover/movie")
    fun getDiscovers(
        @Query("page") page: Int = 1,
        @Query("include_adult") include_adult: Boolean = false,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Single<ResponseDiscover>
}