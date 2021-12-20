package com.example.mydoctor.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DiagnosisResponse(

    @SerializedName("_id")
    val id: String,

    @SerializedName("diagnosis")
    val diagnosis: String,

    @SerializedName("diagnosis_description")
    val diagnosis_description:String,

    @SerializedName("amka_user")
    val amka_user: String,

    @SerializedName("doctor_email")
    val doctor_email: String

)