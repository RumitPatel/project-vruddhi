package com.project.vruddhi.ui.pregnantwoman.model.request

import com.google.gson.annotations.SerializedName

data class PregnantWomanUpdateCounsellingRequest(
    @SerializedName("complications")
    var complications: String? = null, // None
    @SerializedName("couns_no")
    var counsNo: Int? = null, // 1
    @SerializedName("height")
    var height: String? = null, // 165.2
    @SerializedName("hemoglobin_level")
    var hemoglobinLevel: String? = null, // 12.5
    @SerializedName("is_anaemia_counselled")
    var isAnaemiaCounselled: Int? = null, // 0
    @SerializedName("is_diet_counselled")
    var isDietCounselled: Int? = null, // 1
    @SerializedName("is_handwash_counselled")
    var isHandwashCounselled: Int? = null, // 1
    @SerializedName("is_nutrition_kit_given")
    var isNutritionKitGiven: Int? = null, // 1
    @SerializedName("is_soap_given")
    var isSoapGiven: Int? = null, // 1
    @SerializedName("photo")
    var photo: String? = null, // base64_encoded_photo_string
    @SerializedName("weight")
    var weight: String? = null // 11.5
)