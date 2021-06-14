package com.oratakashi.oratamovie.di.module

import com.oratakashi.oratamovie.data.*
import com.oratakashi.oratamovie.di.wire.*
import com.oratakashi.oratamovie.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingRepositoryModule {
    @Home
    @Binds
    abstract fun bindingHome(
        repository: HomeRepository
    ): Repository

    @Popular
    @Binds
    abstract fun bindingPopular(
        repository: PopularRepository
    ): Repository

    @Detail
    @Binds
    abstract fun bindingDetail(
        repository: DetailRepository
    ): Repository

    @Fav
    @Binds
    abstract fun bindingFav(
        repository: FavoriteRepository
    ): Repository

    @Genre
    @Binds
    abstract fun bindingGenre(
        repository: GenreRepository
    ): Repository
}