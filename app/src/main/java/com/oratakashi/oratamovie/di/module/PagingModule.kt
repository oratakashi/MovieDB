package com.oratakashi.oratamovie.di.module

import androidx.paging.PagedList
import com.oratakashi.oratamovie.data.db.RoomDB
import com.oratakashi.oratamovie.data.network.PopularEndpoint
import com.oratakashi.oratamovie.utils.PagingBoundary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {
    @Provides
    @Singleton
    fun provideBoundary(
        endpoint: PopularEndpoint,
        db: RoomDB,
        disposable: CompositeDisposable
    ) : PagingBoundary = PagingBoundary(endpoint, db, disposable)
}