package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName

data class DateAndTimeResponse(

    @SerializedName("appointment_day_time")
    val notAvailableDates : String
)