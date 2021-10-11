package com.example.weatherapp.data.source.remote.helper

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.supervisorScope
import java.lang.Exception

class SafeApiCall<T>(
    private val networkRequest: suspend () -> T,
    private val error: (suspend () -> T)? = null
) {


    suspend fun makeApiNetworkRequest() {
        supervisorScope {

                networkRequest.invoke().safelyHandleResponse()
        }
    }

    private suspend fun T.safelyHandleResponse():T{

       return try {
            this

        }catch (throwable: Throwable){

            if (throwable is CancellationException) {
                throw throwable
            }

            error?.let { errorFallback ->return errorFallback.invoke() }

            throw throwable
        }
    }
}