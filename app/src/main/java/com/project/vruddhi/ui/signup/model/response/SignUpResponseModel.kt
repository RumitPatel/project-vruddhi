package com.project.vruddhi.ui.signup.model.response

import com.google.gson.annotations.SerializedName

data class SignUpResponseModel(
    @SerializedName("status") var status:String ="",
    @SerializedName("uuid") var uuid:String =""
)
