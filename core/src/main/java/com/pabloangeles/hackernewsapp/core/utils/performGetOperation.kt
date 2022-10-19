package com.pabloangeles.hackernewsapp.core.utils

import com.pabloangeles.hackernewsapp.core.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/* A function that takes 3 parameters and returns a Flow<NetworkResult<A>>. */
fun <T, A> performGetOperationDB(
    databaseQuery: suspend () -> T,
    networkCall: suspend () -> NetworkResult<A>,
    saveCallResult: suspend (A?) -> Unit
): Flow<NetworkResult<T>> =
    flow {
          emit(NetworkResult.Loading(true))
          val responseStatus = networkCall.invoke()
          if (responseStatus.data != null) {
            saveCallResult.invoke(responseStatus.data)
          }

          val db = databaseQuery.invoke()
          emit(NetworkResult.Success(db))
          emit(NetworkResult.Loading(false))
        }
        .flowOn(Dispatchers.IO)
