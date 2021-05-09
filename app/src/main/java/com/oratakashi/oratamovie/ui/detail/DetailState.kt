package com.oratakashi.oratamovie.ui.detail

import com.oratakashi.oratamovie.domain.`object`.ResponseDetail

sealed class DetailState {
    object Loading : DetailState()
    data class Result(val data: ResponseDetail) : DetailState()
    data class Error(val error: Throwable) : DetailState()
}
