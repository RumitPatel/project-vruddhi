package com.project.vruddhi.network

import com.google.gson.annotations.SerializedName

class ErrorWrapper {
    @SerializedName("errors")
    var errors: String? = null

    @SerializedName("meta")
    val meta: Meta? = null
}
