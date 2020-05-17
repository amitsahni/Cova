/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.data.model.wrapped

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.covid19.presentation.ui.base.hideLoader
import com.covid19.presentation.ui.base.showLoader
import com.covid19.presentation.ui.base.snackBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

class EventObserver<T>(
    private val activity: AppCompatActivity? = null,
    private val success: (T) -> Unit
) : Observer<Event<Result<T>>> {
    override fun onChanged(event: Event<Result<T>>?) {
        event?.getContentIfNotHandled()?.let { value ->
            when (value) {
                is Result.Loading -> {
                    activity?.showLoader()
                }

                is Result.Success -> {
                    activity?.hideLoader()
                    value.data?.let {
                        Timber.d(it.toString())
                        success(it)
                    }
                }

                is Result.Error -> {
                    activity?.hideLoader()
                    value.error.run {
                        Timber.e(this)
                        activity?.snackBar(message.toString())
                    }
                }
            }
        }
    }
}

class EventResultObserver<T>(
    private val loading: () -> Unit,
    private val success: (T) -> Unit,
    private val error: (Throwable) -> Unit
) : Observer<Event<Result<T>>> {
    override fun onChanged(event: Event<Result<T>>?) {
        event?.getContentIfNotHandled()?.let { value ->
            when (value) {
                is Result.Loading -> {
                    loading()
                }

                is Result.Success -> {
                    success(value.data)
                }

                is Result.Error -> {
                    error(value.error)
                }
            }
        }
    }
}

inline fun <T> Flow<Event<T>>.collectEvent(noinline action: (value: T) -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        collect {
            it.getContentIfNotHandled()?.let {
                action(it)
            }
        }
    }
}