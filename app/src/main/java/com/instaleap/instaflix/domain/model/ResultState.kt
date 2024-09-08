package com.instaleap.instaflix.domain.model

sealed class ResultState<out T> {

    data class Success<T>(val data: T, val isCached: Boolean = false) : ResultState<T>()
    data class Failure(val error: ErrorState) : ResultState<Nothing>()

    val isSuccess: Boolean
        get() = this is Success
}

enum class ErrorState {
    INTERNET_CONNECTION,
    UNKNOWN,
}