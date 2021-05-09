package com.oratakashi.oratamovie.di.module

import com.oratakashi.oratamovie.data.DetailRepository
import com.oratakashi.oratamovie.data.FavoriteRepository
import com.oratakashi.oratamovie.data.HomeRepository
import com.oratakashi.oratamovie.data.PopularRepository
import com.oratakashi.oratamovie.data.db.RoomDB
import com.oratakashi.oratamovie.data.network.DetailEndpoint
import com.oratakashi.oratamovie.data.network.HomeEndpoint
import com.oratakashi.oratamovie.utils.PagingBoundary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(
        endpoint: HomeEndpoint
    ): HomeRepository = HomeRepository(endpoint)

    @Provides
    @Singleton
    fun providePopularRepository(
        db: RoomDB,
        boundary: PagingBoundary
    ): PopularRepository = PopularRepository(db, boundary)

    @Provides
    @Singleton
    fun provideDetailRepository(
        endpoint: DetailEndpoint,
        db: RoomDB
    ): DetailRepository = DetailRepository(endpoint, db)

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        db: RoomDB
    ): FavoriteRepository = FavoriteRepository(db)
}