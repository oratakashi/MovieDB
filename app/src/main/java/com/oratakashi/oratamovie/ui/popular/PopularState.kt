package com.oratakashi.oratamovie.ui.popular

import com.oratakashi.oratamovie.domain.model.discover.Discover

sealed class PopularState {
    object Loading : PopularState()
    data class Result(val data : List<Discover>) : PopularState()
    data class Error(val error : Throwable) : PopularState()
}
