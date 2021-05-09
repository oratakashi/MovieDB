package com.oratakashi.oratamovie.data.network

import com.oratakashi.oratamovie.data.model.discover.ResponseDiscover
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeEndpoint {
    @GET("discover/movie")
    fun getDiscover(
        @Query("include_adult") include_adult: Boolean = false,
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Observable<ResponseDiscover>

    @GET("discover/movie")
    fun getDiscoverYear(
        @Query("year") year: Int,
        @Query("include_adult") include_adult: Boolean = false,
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Observable<ResponseDiscover>
}