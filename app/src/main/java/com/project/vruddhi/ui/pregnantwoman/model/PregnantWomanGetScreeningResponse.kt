package com.project.vruddhi.ui.pregnantwoman.model

import com.google.gson.annotations.SerializedName

data class PregnantWomanGetScreeningResponse(

    @SerializedName("address")
    val address: String? = null, // Udhyognager aa no. 87
    @SerializedName("age")
    val age: Int? = null, // 0
    @SerializedName("counseller_userid")
    val counsellerUserid: Int? = null, // 1
    @SerializedName("current_month_of_pregnancy")
    val currentMonthOfPregnancy: Int? = null, // 1
    @SerializedName("date_of_LMP")
    val dateOfLMP: String? = null, // 2024-06-12
    @SerializedName("dob")
    val dob: String? = null, // 0000-00-00
    @SerializedName("height")
    val height: Int? = null, // 54
    @SerializedName("husband_name")
    val husbandName: String? = null, // Soma Parmar
    @SerializedName("id")
    val id: Int? = null, // 17
    @SerializedName("is_low_BMI")
    val isLowBMI: Int? = null, // 1
    @SerializedName("mobile")
    val mobile: String? = null, // 9104188734
    @SerializedName("registration_date")
    val registrationDate: String? = null, // 2024-01-08
    @SerializedName("registration_number")
    val registrationNumber: String? = null, // A126636570N
    @SerializedName("village")
    val village: String? = null, // Varvala
    @SerializedName("weight")
    val weight: Int? = null, // 34
    @SerializedName("women_name")
    val womenName: String? = null // Rama Soma Parmar
)
