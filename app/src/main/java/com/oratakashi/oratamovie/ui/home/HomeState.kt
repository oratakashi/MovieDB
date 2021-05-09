package com.oratakashi.oratamovie.ui.home

import com.oratakashi.oratamovie.domain.`object`.ResponseHome

sealed class HomeState {
    object Loading : HomeState()
    data class Result(val data : ResponseHome) : HomeState()
    data class Error(val error : Throwable) : HomeState()
}
