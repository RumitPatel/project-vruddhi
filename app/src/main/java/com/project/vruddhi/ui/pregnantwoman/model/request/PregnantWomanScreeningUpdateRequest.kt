package com.project.vruddhi.ui.pregnantwoman.model.request

import com.google.gson.annotations.SerializedName

data class PregnantWomanScreeningUpdateRequest(
    @SerializedName("address")
    var address: String? = null, // Pramukha paramount, gandhinagar
    @SerializedName("age")
    var age: Int? = null, // 79
    @SerializedName("counseller_userid")
    var counsellerUserid: Int? = null, // 1
    @SerializedName("current_month_of_pregnancy")
    var currentMonthOfPregnancy: Int? = null, // 5
    @SerializedName("date_of_LMP")
    var dateOfLMP: String? = null, // 2024-10-12
    @SerializedName("dob")
    var dob: String? = null, // 1979-07-11
    @SerializedName("height")
    var height: Int? = null, // 54
    @SerializedName("husband_name")
    var husbandName: String? = null, // Paresh Patell
    @SerializedName("is_low_BMI")
    var isLowBMI: Int? = null, // 0
    @SerializedName("mobile")
    var mobile: String? = null, // 8878788788
    @SerializedName("registration_date")
    var registrationDate: String? = null, // 2024-08-15
    @SerializedName("registration_number")
    var registrationNumber: String? = null, // A34566787878
    @SerializedName("village")
    var village: String? = null, // Kudasan
    @SerializedName("weight")
    var weight: Int? = null, // 87
    @SerializedName("women_name")
    var womenName: String? = null // Savita patel
)