/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.data.model.wrapped

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            is Loading -> "Loading"
        }
    }
}

/**
 * `true` if [State] is of episodeType [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null


fun <T> Result<T>.success(): T? {
    return when (this) {
        is Result.Success -> this.data
        else -> null
    }
}

fun <T : Any> Result<T>.success(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) data.let(action)
    return this
}


fun <T> Response<T>.toResult(): Result<T> {
    return when(this){
        is Response.Success -> {
            Result.Success(data)
        }
        is Response.Error -> {
            Result.Error(error)
        }
    }
}