package com.example.mydoctor.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DiagnosisResponse(

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("diagnosis")
    val diagnoses: String,

    @Expose
    @SerializedName("diagnosis_prescription")
    val diagnosis_prescription:String,

    @Expose
    @SerializedName("amka_user")
    val amka_user: String,

    @Expose
    @SerializedName("doctor_email")
    val doctor_email: String

)