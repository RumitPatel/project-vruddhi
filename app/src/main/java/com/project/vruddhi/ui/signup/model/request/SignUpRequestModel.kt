package com.project.vruddhi.ui.signup.model.request

import com.google.gson.annotations.SerializedName
data class SignUpRequestModel(
    @SerializedName("name")
    var name:String?=null,
    @SerializedName("password")
    var password:String?=null,
    @SerializedName("phone_number")
    var phoneNumber:String?=null,
    @SerializedName("email")
    var email:String?=null,
    @SerializedName("gender")
    var gender:String?=null,
    @SerializedName("country_id")
    var countryId:String?=null,
    @SerializedName("state_id")
    var stateId:String?=null,
    @SerializedName("date_of_birth")
    var dateOfBirth:String?=null,
    @SerializedName("address_line_1")
    var addressLineOne:String?=null,
    @SerializedName("address_line_2")
    var addressLineTwo:String?=null,
    @SerializedName("street")
    var street:String?=null,
    @SerializedName("zip_code")
    var zipCode:String?=null,
    @SerializedName("device_type")
    var deviceType:String?=null,
    @SerializedName("device_token")
    var deviceToken:String?=null,
    @SerializedName("icon_id")
    var iconId:String?=null
)
