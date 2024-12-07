package com.project.vruddhi.network

import com.google.gson.annotations.SerializedName

class Meta {
    @SerializedName("status")
    var isStatus = false

    @SerializedName("message")
    var message: String? = null

    @SerializedName("message_code")
    var messageCode: String? = null

    @SerializedName("status_code")
    var statusCode = 0
}