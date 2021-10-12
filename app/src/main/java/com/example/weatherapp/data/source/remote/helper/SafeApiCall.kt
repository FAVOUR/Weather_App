package com.example.weatherapp.data.source.remote.helper

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.supervisorScope
import retrofit2.Response
import java.lang.Exception

class SafeApiCall<T>(
    private val networkRequest: suspend () -> Response<T>
//    , private val error: (suspend () -> T)? = null
) {


    suspend fun makeApiNetworkRequest():ResponseFromServer<T> {
        return supervisorScope {

                networkRequest().safelyHandleResponse()
        }
    }

    private fun Response<T>.safelyHandleResponse():ResponseFromServer<T>{

       return try {
            this.categorizeResponse()

        }catch (throwable: Throwable){

            if (throwable is CancellationException) {
                throw throwable
            }

           //TODO CORRECT THIS
//            error?.let { errorFallback ->return ResponseFromServer.Error(Exception(errorFallback() as String) ) }

            throw throwable
        }
    }


    private  fun Response<T>.categorizeResponse():ResponseFromServer<T>{

       return if(isSuccessful){
            ResponseFromServer.Success(this.body()!!)
        }else{
           ResponseFromServer.Error(Exception("An Error Occurred"))
        }
    }
}

 sealed  class ResponseFromServer< out T>{

     class Success <out T>(val data:T):ResponseFromServer<T>()
     class Error(val exception:Exception):ResponseFromServer<Nothing>()
//     class Exception <R>(data:Throwable):ResponseFromServer()
 }