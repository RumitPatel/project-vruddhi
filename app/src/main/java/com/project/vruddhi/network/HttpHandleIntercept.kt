package com.project.vruddhi.network

import android.util.Log
import com.project.vruddhi.BuildConfig
import com.project.vruddhi.network.ApiConstants.ACCEPT
import com.project.vruddhi.network.ApiConstants.ACCEPT_VALUE
import com.project.vruddhi.network.ApiConstants.ANDROID
import com.project.vruddhi.network.ApiConstants.AUTHORIZATION
import com.project.vruddhi.network.ApiConstants.CONTENT_TYPE
import com.project.vruddhi.network.ApiConstants.CONTENT_TYPE_VALUE
import com.project.vruddhi.network.ApiConstants.LANG_CODE
import com.project.vruddhi.network.ApiConstants.LANG_CODE_EN
import com.project.vruddhi.network.ApiConstants.PLATEFORM
import com.project.vruddhi.utils.DebugLog
import com.project.vruddhi.utils.MyPreference
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import com.project.vruddhi.network.ApiConstants.RESPONSE_CODE_401
import com.project.vruddhi.network.ApiConstants.RESPONSE_CODE_500
import com.project.vruddhi.network.ApiConstants.VERSION
import com.project.vruddhi.utils.PrefKey

/**
 * Intercepter class
 */
class HttpHandleIntercept(
    val myPreference: MyPreference
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().headers(getJsonHeader()).build()
        val response: Response?

        response = chain.proceed(request)
        if (response.code == RESPONSE_CODE_401) {
            return generateCustomResponse(
                RESPONSE_CODE_401, "",
                chain.request()
            )!!

        } else if (response.code == RESPONSE_CODE_500) {
            return generateCustomResponse(
                RESPONSE_CODE_500, "",
                chain.request()
            )!!
        }

        return response
    }

    private fun getJsonHeader(): Headers {
        val builder = Headers.Builder()
        builder.add(CONTENT_TYPE, CONTENT_TYPE_VALUE)
        builder.add(ACCEPT, CONTENT_TYPE_VALUE)
        builder.add(PLATEFORM, ANDROID)
        builder.add(VERSION, "${BuildConfig.VERSION_CODE}")
        builder.add(LANG_CODE, LANG_CODE_EN)
        //builder.add(AUTHORIZATION,"Bearer ${myPreference.getValueString(PrefKey.TOKEN,"")}")
        Log.d("APP_TOKEN",myPreference.getValueString(PrefKey.TOKEN,"")!!)

        return builder.build()
    }

    /**
     * generate custom response for exception
     */
    private fun generateCustomResponse(code: Int, message: String, request: Request): Response? {
        return try {
            val body = getJSONObjectForException(message, code).toString()
                .toResponseBody("application/json".toMediaTypeOrNull())
            Response.Builder()
                .code(code)
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(body)
                .message(message)
                .build()
        } catch (ex: Exception) {
            DebugLog.print(ex)
            null
        }
    }

    /**
     * generate JSON object for error case
     */
    private fun getJSONObjectForException(message: String, code: Int): JSONObject {

        try {
            val jsonMainObject = JSONObject()

            val `object` = JSONObject()
            `object`.put("status", false)
            `object`.put("message", message)
            `object`.put("message_code", code)
            `object`.put("status_code", code)

            jsonMainObject.put("meta", `object`)

            val obj = JSONObject()
            obj.put("error", JSONArray().put(message))

            jsonMainObject.put("errors", obj)

            return jsonMainObject
        } catch (e: JSONException) {
            DebugLog.print(e)
            return JSONObject()
        }
    }
}