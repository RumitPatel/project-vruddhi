package com.project.vruddhi.base

import android.util.Log
import androidx.navigation.NavController
import com.project.vruddhi.network.*
import com.project.vruddhi.utils.Constants
import com.project.vruddhi.utils.DebugLog
import com.project.vruddhi.utils.Utils.getGsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject


/**
 * Common class for API calling
 */

open class BaseRepository {

    @Inject
    lateinit var navController: NavController
    suspend fun <T : Any> makeAPICall(
        call: suspend () -> Response<ResponseData<T>>
    ): Flow<ResponseHandler<ResponseData<T>?>> {

        return flow {

            emit(ResponseHandler.Loading(true,0))

            var response:Response<ResponseData<T>>? = null
            var str:String?  = ""

            try {
                response = call.invoke()
                str = response.errorBody()?.string()
            }catch (e:Exception){
                e.printStackTrace()
                e.message?.let { Log.d("API_EXCEPTION", it) }
            }

            try {
                when (response?.code()) {

                    ApiConstants.RESPONSE_CODE_401 -> {
                        emit(ResponseHandler.Loading(false,response.code()))
                        emit(parseUnAuthorizeResponse(str))
                    }

                    ApiConstants.RESPONSE_CODE_404 ->{

                        if(!str.isNullOrEmpty()){
                            emit(ResponseHandler.Loading(false,response.code()))
                            emit(parseServerSideErrorResponse(str))
                        }
                    }

                    ApiConstants.RESPONSE_CODE_422 -> {
                        if (response.message().equals(HttpErrorCode.UNAUTHORIZED.code)) {
                            emit(ResponseHandler.Loading(false,response.code()))
                            //navController.navigate(R.id.loginFragment)
                        } else {
                            emit(ResponseHandler.Loading(false,response.code()))
                            emit(parseServerSideErrorResponse(str!!))
                        }

                    }

                    ApiConstants.RESPONSE_CODE_500 -> {
                        emit(ResponseHandler.Loading(false,response.code()))
                        emit(
                            ResponseHandler.OnFailed(
                                HttpErrorCode.NOT_DEFINED.code,
                                "API Exception",
                                response.body()?.message.toString()
                            )
                        )
                    }

                    in (ApiConstants.RESPONSE_CODE_200..ApiConstants.RESPONSE_CODE_500) -> {

                        when (response?.body()?.statusCode) {
                            ApiConstants.RESPONSE_CODE_400 -> {
                                emit(ResponseHandler.Loading(false,response.code()))
                                emit(
                                    ResponseHandler.OnFailed(
                                        response.body()?.statusCode,
                                        response.body()?.message,
                                        "0"
                                    )
                                )
                            }
                            ApiConstants.RESPONSE_CODE_401 -> {
                                if (response.body()?.statusCode.toString().equals(HttpErrorCode.UNAUTHORIZED.code)) {
                                    emit(ResponseHandler.Loading(false,response.code()))
                                    //navController.navigate(R.id.loginFragment)
                                } else {
                                    emit(ResponseHandler.Loading(false,response.code()))
                                    emit(
                                        ResponseHandler.OnFailed(
                                            HttpErrorCode.UNAUTHORIZED.code,
                                            response.body()?.message,
                                            response.body()?.statusCode!!.toString()
                                        )
                                    )
                                }
                            }
                            else -> {
                                emit(ResponseHandler.Loading(false,response?.code()))
                                emit(ResponseHandler.OnSuccessResponse(response?.body()))
                            }
                        }
                    }

                    else -> {
                        emit(ResponseHandler.Loading(false,response?.code()))
                        emit(parseUnKnownStatusCodeResponse(str))
                    }
                }
            } catch (e: Exception) {
                DebugLog.print(e)
                emit(ResponseHandler.Loading(false,response?.code()))
                emit(ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, e.message, ""))
            }

        }.flowOn(Dispatchers.IO).catch {}
    }


    suspend fun <T : Any> makeAPICallList(
        call: suspend () -> Response<ResponseDataList<T>>
    ): Flow<ResponseHandler<ResponseDataList<T>?>> {

        return flow {

            emit(ResponseHandler.Loading(true,0))


            var str:String?  = ""
            var response:Response<ResponseDataList<T>>? = null

            try {
                response = call.invoke()
                str = response.errorBody()?.string()
            }catch (e:Exception){
                e.printStackTrace()
            }

            try {
                when (response?.code()) {

                    ApiConstants.RESPONSE_CODE_401 -> {
                        emit(ResponseHandler.Loading(false,response.code()))
                        emit(parseUnAuthorizeResponse(str))
                    }

                    ApiConstants.RESPONSE_CODE_422 -> {
                        emit(ResponseHandler.Loading(false,response.code()))
                        if (response.message().equals(HttpErrorCode.UNAUTHORIZED.code)) {
                            //navController.navigate(R.id.loginFragment)
                        } else {
                            emit(parseServerSideErrorResponse(str))
                        }

                    }

                    ApiConstants.RESPONSE_CODE_500 -> {
                        emit(ResponseHandler.Loading(false,response.code()))
                        emit(
                            ResponseHandler.OnFailed(
                                HttpErrorCode.NOT_DEFINED.code,
                                "",
                                response.body()?.statusCode.toString()
                            )
                        )
                    }

                    in (ApiConstants.RESPONSE_CODE_200..ApiConstants.RESPONSE_CODE_300) -> {

                        when (response?.body()?.statusCode) {
                            ApiConstants.RESPONSE_CODE_400 -> {
                                emit(ResponseHandler.Loading(false,response.code()))
                                ResponseHandler.OnFailed(
                                    response.body()?.statusCode,
                                    response.body()?.message,
                                    "0"
                                )
                            }
                            ApiConstants.RESPONSE_CODE_401 -> {
                                emit(ResponseHandler.Loading(false,response.code()))
                                if (response.body()?.statusCode.toString().equals(HttpErrorCode.UNAUTHORIZED.code)) {
                                    //navController.navigate(R.id.loginFragment)
                                } else {
                                    emit(
                                        ResponseHandler.OnFailed(
                                            HttpErrorCode.UNAUTHORIZED.code,
                                            response.body()?.message,
                                            response.body()?.statusCode!!.toString()
                                        )
                                    )
                                }
                            }
                            else -> {
                                emit(ResponseHandler.Loading(false,response?.code()))
                                emit(ResponseHandler.OnSuccessResponse(response?.body()))
                            }
                        }
                    }

                    else -> {
                        emit(ResponseHandler.Loading(false,response?.code()))
                        emit(parseUnKnownStatusCodeResponse(str))
                    }
                }
            } catch (e: Exception) {
                DebugLog.print(e)
                emit(ResponseHandler.Loading(false,response?.code()))
                emit(ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, e.message, ""))
            }

        }.flowOn(Dispatchers.IO).catch {
            emit(ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "", ""))
        }
    }

    /*Response parsing for 401 status code*/
    private fun parseUnAuthorizeResponse(bodyString: String?): ResponseHandler.OnFailed {
        val message: String
        val responseWrapper: ErrorWrapper = getGsonObject().fromJson(bodyString, ErrorWrapper::class.java)
        message = if (responseWrapper.meta!!.statusCode == 401) {
            if (responseWrapper.errors != null) {
                responseWrapper.errors.toString()
            } else {
                responseWrapper.meta.message.toString()
            }
        } else {
            responseWrapper.meta.message.toString()
        }
        return ResponseHandler.OnFailed(
            HttpErrorCode.UNAUTHORIZED.code,
            message,
            responseWrapper.meta.messageCode.toString()
        )
    }

    /*Response parsing for 422 status code*/
    private fun parseServerSideErrorResponse(bodyString: String?): ResponseHandler.OnFailed {
        var message = ""

        val responseWrapper: ErrorWrapper = getGsonObject().fromJson(bodyString, ErrorWrapper::class.java)

        message = if (responseWrapper.meta!!.statusCode == 422) {
            if (responseWrapper.errors != null) {
                responseWrapper.errors.toString()
            } else {
                responseWrapper.meta.message.toString()
            }
        } else {
            responseWrapper.meta.message.toString()
        }
        return ResponseHandler.OnFailed(
            HttpErrorCode.EMPTY_RESPONSE.code,
            message,
            responseWrapper.meta.messageCode.toString()
        )
    }

    /*Response parsing for unknown status code*/
    private fun parseUnKnownStatusCodeResponse(bodyString: String?): ResponseHandler.OnFailed {
        return if (bodyString != null && bodyString.isNotEmpty()) {
            val responseWrapper: ErrorWrapper = getGsonObject().fromJson(bodyString, ErrorWrapper::class.java)
            val message = if (responseWrapper.meta!!.statusCode == 422) {
                if (responseWrapper.errors != null) {
                    responseWrapper.errors
                } else {
                    responseWrapper.meta.message.toString()
                }
            } else {
                responseWrapper.meta.message.toString()
            }
            return if (message?.isEmpty() == true) {
                ResponseHandler.OnFailed(
                    HttpErrorCode.EMPTY_RESPONSE.code,
                    message,
                    responseWrapper.meta.messageCode.toString()
                )
            } else {
                ResponseHandler.OnFailed(
                    HttpErrorCode.NOT_DEFINED.code,
                    message,
                    responseWrapper.meta.messageCode.toString()
                )
            }
        } else {
            ResponseHandler.OnFailed(
                HttpErrorCode.NOT_DEFINED.code,
                Constants.NO_INTERNET,
                ""
            )
        }
    }
}
