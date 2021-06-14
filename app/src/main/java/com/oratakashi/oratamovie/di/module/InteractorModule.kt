package com.oratakashi.oratamovie.di.module

import com.oratakashi.oratamovie.di.wire.*
import com.oratakashi.oratamovie.domain.interactor.*
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

    @Provides
    @Singleton
    fun provideGenreInteractor(
        @Genre repository: Repository
    ): GenreInteractor = GenreInteractor(repository)
}