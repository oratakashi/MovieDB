package com.oratakashi.oratamovie.di.module

import com.oratakashi.oratamovie.di.wire.Detail
import com.oratakashi.oratamovie.di.wire.Fav
import com.oratakashi.oratamovie.di.wire.Home
import com.oratakashi.oratamovie.di.wire.Popular
import com.oratakashi.oratamovie.domain.interactor.DetailInteractor
import com.oratakashi.oratamovie.domain.interactor.FavoriteInteractor
import com.oratakashi.oratamovie.domain.interactor.HomeInteractor
import com.oratakashi.oratamovie.domain.interactor.PopularInteractor
import com.oratakashi.oratamovie.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InteractorModule {
    @Provides
    @Singleton
    fun provideHomeInteractor(
        @Home repository: Repository
    ): HomeInteractor = HomeInteractor(repository)

    @Provides
    @Singleton
    fun providePopularInteractor(
        @Popular repository: Repository
    ): PopularInteractor = PopularInteractor(repository)

    @Provides
    @Singleton
    fun provideDetailInteractor(
        @Detail repository: Repository
    ): DetailInteractor = DetailInteractor(repository)

    @Provides
    @Singleton
    fun provideFavoriteInteractor(
        @Fav repository: Repository
    ): FavoriteInteractor = FavoriteInteractor(repository)
}