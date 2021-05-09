package com.oratakashi.oratamovie.di.module

import com.oratakashi.oratamovie.di.wire.Detail
import com.oratakashi.oratamovie.di.wire.Fav
import com.oratakashi.oratamovie.di.wire.Home
import com.oratakashi.oratamovie.di.wire.Popular
import com.oratakashi.oratamovie.domain.interactor.DetailInteractor
import com.oratakashi.oratamovie.domain.interactor.FavoriteInteractor
import com.oratakashi.oratamovie.domain.interactor.HomeInteractor
import com.oratakashi.oratamovie.domain.interactor.PopularInteractor
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
}