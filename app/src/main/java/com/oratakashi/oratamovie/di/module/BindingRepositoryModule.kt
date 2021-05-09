package com.oratakashi.oratamovie.di.module

import com.oratakashi.oratamovie.data.DetailRepository
import com.oratakashi.oratamovie.data.FavoriteRepository
import com.oratakashi.oratamovie.data.HomeRepository
import com.oratakashi.oratamovie.data.PopularRepository
import com.oratakashi.oratamovie.di.wire.Detail
import com.oratakashi.oratamovie.di.wire.Fav
import com.oratakashi.oratamovie.di.wire.Home
import com.oratakashi.oratamovie.di.wire.Popular
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
}