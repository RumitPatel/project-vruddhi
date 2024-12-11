package com.project.vruddhi.ui.pregnantwoman.model.request

import com.google.gson.annotations.SerializedName

data class PregnantWomanUpdateRegistrationRequest(
    @SerializedName("education")
    var education: String? = null, // SSC
    @SerializedName("illness")
    var illness: String? = null, // tb
    @SerializedName("is_ANM_registered")
    var isANMRegistered: Int? = null, // 1
    @SerializedName("no_of_abortion")
    var noOfAbortion: Int? = null, // 5
    @SerializedName("no_of_live_children")
    var noOfLiveChildren: Int? = null, // 5
    @SerializedName("no_of_pregnancy")
    var noOfPregnancy: Int? = null, // 5
    @SerializedName("occupation")
    var occupation: String? = null, // House wife
    @SerializedName("screening_id")
    var screeningId: Int? = null, // 101
    @SerializedName("user_id")
    var userId: Int? = null // 202
)