package com.project.vruddhi.ui.signin.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("message")
    val message: String? = null, // Login successfull.
    @SerializedName("statusCode")
    val statusCode: Int? = null, // 200
    @SerializedName("success")
    val success: Boolean? = null // true
) {
    data class Data(
        @SerializedName("id")
        val id: String? = null, // 2
        @SerializedName("password")
        val password: String? = null, // 6cb018f3ade3e9314269a9dea6271028
        @SerializedName("username")
        val username: String? = null, // deval.bhavsar@gmail.com
        @SerializedName("villages")
        val villages: String? = null // vartej
    )
}