package com.oratakashi.oratamovie.ui.genre

import com.oratakashi.oratamovie.domain.`object`.ResponseGenre

sealed class GenreState {
    object Loading : GenreState()

    data class Result(val data: ResponseGenre) : GenreState()
    data class Error(val error: Throwable) : GenreState()
}
