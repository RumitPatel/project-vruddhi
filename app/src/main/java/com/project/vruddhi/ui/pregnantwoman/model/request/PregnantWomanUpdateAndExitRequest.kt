package com.project.vruddhi.ui.pregnantwoman.model.request


import com.google.gson.annotations.SerializedName

data class PregnantWomanUpdateAndExitRequest(
    @SerializedName("baby_complications")
    var babyComplications: String? = null, // baby complications
    @SerializedName("birth_weight")
    var birthWeight: String? = null, // 12.5
    @SerializedName("d_o_date")
    var dODate: String? = null, // 2024-10-12
    @SerializedName("death_reason")
    var deathReason: String? = null, // death reason
    @SerializedName("duration_of_pregnancy")
    var durationOfPregnancy: Int? = null, // 3
    @SerializedName("is_delivery")
    var isDelivery: Int? = null, // 1
    @SerializedName("is_migrated_area")
    var isMigratedArea: Int? = null, // 1
    @SerializedName("is_miscarriage")
    var isMiscarriage: Int? = null, // 0
    @SerializedName("m_o_date")
    var mODate: String? = null, // 2024-10-12
    @SerializedName("migrated_village")
    var migratedVillage: String? = null, // Sargasan
    @SerializedName("mother_complications")
    var motherComplications: String? = null, // mother complications
    @SerializedName("p_o_date")
    var pODate: String? = null // 2024-10-12
)