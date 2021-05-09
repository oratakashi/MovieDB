package com.oratakashi.oratamovie.data.network

import com.oratakashi.oratamovie.data.model.cast.ResponseCast
import com.oratakashi.oratamovie.data.model.detail.ResponseDetail
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailEndpoint {
    @GET("movie/{id}")
    fun getDetail(
        @Path("id") id: Int
    ): Observable<ResponseDetail>

    @GET("movie/{id}/credits")
    fun getCast(
        @Path("id") id: Int
    ): Observable<ResponseCast>
}