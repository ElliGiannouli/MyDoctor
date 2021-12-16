package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName

data class BookedAppointmentResponse(

    @SerializedName("message")
    val confirmationMessage: String
)
