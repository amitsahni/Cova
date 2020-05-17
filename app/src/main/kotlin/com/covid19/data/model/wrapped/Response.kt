/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.data.model.wrapped

sealed class Response<out T> {

    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val error: Throwable) : Response<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
        }
    }
}

/**
 * `true` if [State] is of episodeType [Success] & holds non-null [Success.data].
 */
val Response<*>.succeeded
    get() = this is Response.Success && data != null


fun <T> Response<T>.success(): T? {
    return when (this) {
        is Response.Success -> this.data
        else -> null
    }
}

fun <T : Any> Response<T>.success(action: (T) -> Unit): Response<T> {
    if (this is Response.Success) data?.let(action)
    return this
}

fun <T> retrofit2.Response<T>.toResponseBody(): Response<T> {
    return if (isSuccessful) {
        body()?.let {
            Response.Success(it)
        } ?: Response.Error(Throwable("Something went wrong"))
    } else {
        Response.Error(Throwable(errorBody()?.string()))
    }
}