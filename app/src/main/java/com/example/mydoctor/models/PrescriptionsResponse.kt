package com.example.mydoctor.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PrescriptionsResponse(

    @SerializedName("_id")
    val id: String,

    @SerializedName("amka_user")
    val amka_user: String,

    @SerializedName("prescription")
    val prescriptions: String,

    @SerializedName("doctor_email")
    val doctor_email: String,

    @SerializedName("prescription_description")
    val prescriptions_description: String

)