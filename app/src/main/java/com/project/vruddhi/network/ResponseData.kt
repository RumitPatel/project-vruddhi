package com.project.vruddhi.network

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable
import org.json.JSONObject

open class ResponseData<T> {

    @SerializedName("data")
    var data: T? = null

    override fun toString(): String {
        return "ResponseWrapper{" +
                "data=" + data.toString()
    }

    @SerializedName("statusCode")
    val statusCode: Int? = null

    @Nullable
    @SerializedName("success")
    var success: Boolean? = null

    @Nullable
    @SerializedName("message")
    var message: String? = null

}
