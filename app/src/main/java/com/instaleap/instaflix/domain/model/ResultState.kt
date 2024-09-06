package com.instaleap.instaflix.domain.model

sealed class ResultState<out T> {

    /**
     * Contains the object of type [T] if it's a success.
     */
    data class Success<T>(val data: T) : ResultState<T>()

    /**
     * Contains an error [ErrorState] if it's a failure.
     */
    data class Failure(val error: ErrorState) : ResultState<Nothing>()

    /**
     * True if the result is a success, false otherwise.
     */
    val isSuccess: Boolean
        get() = this is Success
}

/**
 * Enum describing possible error states for operations.
 */
enum class ErrorState {
    NO_DATA_FOUND,
    TIMEOUT,
    UNKNOWN,
}
