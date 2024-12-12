package com.project.vruddhi.ui.pregnantwoman.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PregnantWomanListResponse(

    @field:SerializedName("women_name")
    val womenName: String? = null,

    @field:SerializedName("mobile")
    val mobile: String? = null,

    @field:SerializedName("id")
    val id: Long? = null,

    @field:SerializedName("village")
    val village: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("age")
    val age: Int? = null
) : Parcelable
