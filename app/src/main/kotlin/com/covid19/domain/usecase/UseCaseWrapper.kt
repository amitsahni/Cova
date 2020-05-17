/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.covid19.data.model.wrapped.Event
import com.covid19.data.model.wrapped.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext

interface UseCase<P, R> : BaseUseCase<P, R> {
    override suspend fun onExecute(parameter: P?): Result<R>
}

interface BaseUseCase<P, R> {

    suspend fun onExecute(parameter: P?): Result<R>

    /*fun execute(parameter: P? = null, scope: CoroutineScope): LiveData<Event<Result<R>>> {
        *//*val mutableLiveData = MutableLiveData<Event<Result<R>>>()
        mutableLiveData.postValue(Event(Result.Loading))
        val handlerException = CoroutineExceptionHandler { _, throwable ->
            mutableLiveData.postValue(Event(Result.Exception(throwable)))
        }
        scope.plus(Dispatchers.IO).launch(handlerException) {
            flow {
                emit(Event(Result.Loading))
                emit(Event(onExecute(parameter)))
            }.catch {
                emit(Event(Result.Exception(it)))
            }.asLiveData(Dispatchers.IO)
        }
        flow {
            emit(Event(Result.Loading))
            emit(Event(onExecute(parameter)))
        }.catch {
            emit(Event(Result.Exception(it)))
        }.flowOn(Dispatchers.IO).asLiveData(Dispatchers.IO)*//*

        val mutableLiveData1 =  liveData<Event<Result<R>>>(Dispatchers.IO) {
            val handlerException = CoroutineExceptionHandler { _, throwable ->
                flow {
                    emit(Event(Result.Exception(throwable)))
                }

            }
            scope.plus(Dispatchers.IO).launch() {
                emitSource(flow {
                    emit(Event(Result.Loading))
                    emit(Event(onExecute(parameter)))
                }.catch {
                    emit(Event(Result.Exception(it)))
                }.asLiveData(Dispatchers.IO))
            }
        }
        return mutableLiveData1
    }*/

    fun execute(scope: CoroutineScope, parameter: P? = null): LiveData<Event<Result<R>>> {
        return liveData {
            emit(Event(Result.Loading))
            emitSource(
                withContext(scope.plus(Dispatchers.IO).coroutineContext) {
                    flow {
                        emit(Event(onExecute(parameter)))
                    }.catch {
                            emit(Event(Result.Error(it)))
                        }.flowOn(Dispatchers.IO)
                        .asLiveData(Dispatchers.IO)
                })
        }
    }

    /*fun execute(parameter: P? = null, scope: CoroutineScope): LiveData<Event<Result<R>>> {
        val mutableLiveData = MutableLiveData<Event<Result<R>>>()
        mutableLiveData.postValue(Event(Result.Loading))
        val handlerException = CoroutineExceptionHandler { _, throwable ->
            mutableLiveData.postValue(Event(Result.Exception(throwable)))
        }
        scope.plus(Dispatchers.IO).launch(handlerException) {
            mutableLiveData.postValue(Event(onExecute(parameter)))
        }
        return mutableLiveData
    }*/
}