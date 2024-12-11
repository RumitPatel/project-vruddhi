package com.project.vruddhi.ui.pregnantwoman.model

import com.google.gson.annotations.SerializedName

data class PregnantWomanCounsellingResponse(
    @SerializedName("complications")
    val complications: String? = null, // Mild dizziness
    @SerializedName("couns_no")
    val counsNo: Int? = null, // 2
    @SerializedName("height")
    val height: Double? = null, // 170.3
    @SerializedName("hemoglobin_level")
    val hemoglobinLevel: Double? = null, // 11.8
    @SerializedName("id")
    val id: Double? = null, // 2
    @SerializedName("is_anaemia_counselled")
    val isAnaemiaCounselled: Int? = null, // 1
    @SerializedName("is_diet_counselled")
    val isDietCounselled: Int? = null, // 1
    @SerializedName("is_handwash_counselled")
    val isHandwashCounselled: Int? = null, // 0
    @SerializedName("is_nutrition_kit_given")
    val isNutritionKitGiven: Int? = null, // 0
    @SerializedName("is_soap_given")
    val isSoapGiven: Int? = null, // 1
    @SerializedName("photo")
    val photo: String? = null, // base64_encoded_photo_string
    @SerializedName("screening_id")
    val screeningId: Long? = null, // 14
    @SerializedName("weight")
    val weight: Double? = null // 60.2
)