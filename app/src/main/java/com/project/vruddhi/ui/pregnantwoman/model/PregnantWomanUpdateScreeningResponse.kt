package com.project.vruddhi.ui.pregnantwoman.model

import com.google.gson.annotations.SerializedName

data class PregnantWomanUpdateScreeningResponse(

    @field:SerializedName("women_name")
    val womenName: String? = null,

    @field:SerializedName("mobile")
    val mobile: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("village")
    val village: String? = null,

    @field:SerializedName("address")
    val address: String? = null,


    @field:SerializedName("age")
    val age: Int? = null
)
