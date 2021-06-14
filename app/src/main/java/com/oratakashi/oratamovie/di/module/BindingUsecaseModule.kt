package com.oratakashi.oratamovie.di.module

import com.oratakashi.oratamovie.di.wire.*
import com.oratakashi.oratamovie.domain.interactor.*
import com.oratakashi.oratamovie.domain.usecase.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingUsecaseModule {
    @Home
    @Binds
    abstract fun bindingHome(
        useCase: HomeInteractor
    ): UseCase

    @Popular
    @Binds
    abstract fun bindingPopular(
        useCase: PopularInteractor
    ): UseCase

    @Detail
    @Binds
    abstract fun bindingDetail(
        useCase: DetailInteractor
    ): UseCase

    @Fav
    @Binds
    abstract fun bindingFavorite(
        useCase: FavoriteInteractor
    ): UseCase

    @Genre
    @Binds
    abstract fun bindingGenre(
        useCase: GenreInteractor
    ): UseCase
}