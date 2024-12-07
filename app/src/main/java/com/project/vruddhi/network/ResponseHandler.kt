package com.project.vruddhi.network

/**
 * ResponseHandler class
 */
sealed class ResponseHandler<out T> {
    class Loading(val isLoading:Boolean,val code: Int?) : ResponseHandler<Nothing>()
    class OnFailed(val code: Int?, val message: String?, val messageCode: String?) :
        ResponseHandler<Nothing>()

    class OnSuccessResponse<T>(val response: T) : ResponseHandler<T>()
}