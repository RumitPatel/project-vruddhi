package com.project.vruddhi.ui.pregnantwoman.model

import com.google.gson.annotations.SerializedName
data class PregnantWomanGetScreeningAllResponse(
    @SerializedName("address")
    val address: String? = null, // Kudasan
    @SerializedName("age")
    val age: Int? = null, // 79
    @SerializedName("baby_complications")
    val babyComplications: String? = null, // 0
    @SerializedName("birth_weight")
    val birthWeight: Double? = null, // 12.5
    @SerializedName("complications")
    val complications: String? = null, // tb
    @SerializedName("couns_no")
    val counsNo: Int? = null, // 2
    @SerializedName("counseller_userid")
    val counsellerUserid: Double? = null, // 0
    @SerializedName("current_month_of_pregnancy")
    val currentMonthOfPregnancy: Int? = null, // 5
    @SerializedName("d_o_date")
    val dODate: String? = null, // 2024-10-12 00:00:00
    @SerializedName("date_of_LMP")
    val dateOfLMP: String? = null, // 2024-10-12
    @SerializedName("is_mother_death")
    var isMotherDeath: Int? = null, // 1
    @SerializedName("death_reason")
    val deathReason: String? = null, // death reason
    @SerializedName("dob")
    val dob: String? = null, // 1979-07-11
    @SerializedName("duration_of_pregnancy")
    val durationOfPregnancy: String? = null, // 3
    @SerializedName("education")
    val education: String? = null,
    @SerializedName("height")
    val height: Double? = null, // 77.65
    @SerializedName("hemoglobin_level")
    val hemoglobinLevel: Double? = null, // 9
    @SerializedName("husband_name")
    val husbandName: String? = null, // Paresh Patell
    @SerializedName("id")
    val id: Long? = null, // 2
    @SerializedName("illness")
    val illness: String? = null,
    @SerializedName("is_ANM_registered")
    val isANMRegistered: Int? = null, // 0
    @SerializedName("is_any_illness")
    val isAnyIllness: Int? = null, // 0
    @SerializedName("is_anaemia_counselled")
    val isAnaemiaCounselled: Int? = null, // 0
    @SerializedName("is_delivery")
    val isDelivery: Int? = null, // 1
    @SerializedName("is_diet_counselled")
    val isDietCounselled: Int? = null, // 0
    @SerializedName("is_handwash_counselled")
    val isHandwashCounselled: Int? = null, // 1
    @SerializedName("is_low_BMI")
    val isLowBMI: Int? = null, // 0
    @SerializedName("is_migrated_area")
    val isMigratedArea: Int? = null, // 1
    @SerializedName("is_miscarriage")
    val isMiscarriage: Int? = null, // 0
    @SerializedName("is_nutrition_kit_given")
    val isNutritionKitGiven: Int? = null, // 0
    @SerializedName("is_soap_given")
    val isSoapGiven: Int? = null, // 0
    @SerializedName("mode_of_delivery")
    val modeOfDelivery: String? = null, // 2024-10-12 00:00:00
    @SerializedName("migrated_village")
    val migratedVillage: String? = null, // Sargasan
    @SerializedName("mobile")
    val mobile: String? = null, // 8878788788
    @SerializedName("isMotherComplications")
    var isMotherComplications:Int? = null, // 1
    @SerializedName("mother_complications")
    val motherComplications: String? = null, // mother complications
    @SerializedName("no_of_abortion")
    val noOfAbortion: Int? = null, // 0
    @SerializedName("no_of_live_children")
    val noOfLiveChildren: Int? = null, // 0
    @SerializedName("no_of_pregnancy")
    val noOfPregnancy: Int? = null, // 0
    @SerializedName("occupation")
    val occupation: String? = null,
    @SerializedName("place_of_delivery")
    val placeOfDelivery : String? = null, // 2024-10-12 00:00:00
    @SerializedName("photo")
    val photo: String? = null, // base64-encoded-image-data
    @SerializedName("registration_date")
    val registrationDate: String? = null, // 0000-00-00
    @SerializedName("registration_number")
    val registrationNumber: String? = null,
    @SerializedName("screening_id")
    val screeningId: Long? = null, // 14
    @SerializedName("status")
    val status: String? = null, // received
    @SerializedName("user_id")
    val userId: Long? = null, // 0
    @SerializedName("village")
    val village: String? = null,
    @SerializedName("weight")
    val weight: Double? = null, // 88.9
    @SerializedName("women_name")
    val womenName: String? = null // Savita patel
)