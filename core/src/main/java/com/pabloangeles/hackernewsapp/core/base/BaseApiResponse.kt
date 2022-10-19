package com.pabloangeles.hackernewsapp.core.base

import com.pabloangeles.hackernewsapp.core.network.NetworkResult
import retrofit2.Response

/* A class that contains a suspend function that takes a suspend function as a parameter. */
abstract class BaseApiResponse {

    /* This function is a suspend function that takes a suspend function as a parameter. */
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }


    /* A private function that returns a NetworkResult.Error with the error message. */
    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Fail call  api: $errorMessage")

}