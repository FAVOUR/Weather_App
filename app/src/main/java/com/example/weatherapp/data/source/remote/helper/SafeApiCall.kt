package com.example.weatherapp.data.source.remote.helper

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.supervisorScope
import retrofit2.Response

class SafeApiCall<T>(
    private val networkRequest: suspend () -> Response<T>
) {


    suspend fun makeApiNetworkRequest(): ResponseFromServer<T> {
        return supervisorScope {

            networkRequest().safelyHandleResponse()
        }
    }

    private fun Response<T>.safelyHandleResponse(): ResponseFromServer<T> {

        return try {
            this.categorizeResponse()

        } catch (throwable: Throwable) {

            if (throwable is CancellationException) {
                throw throwable
            }

            throw throwable
        }
    }


    private fun Response<T>.categorizeResponse(): ResponseFromServer<T> {

        return if (isSuccessful) {
            ResponseFromServer.Success(this.body()!!)
        } else {
            ResponseFromServer.Error(Exception("An Error Occurred"))
        }
    }
}

sealed class ResponseFromServer<out T> {

    class Success<out T>(val data: T) : ResponseFromServer<T>()
    class Error(val exception: Exception) : ResponseFromServer<Nothing>()
}