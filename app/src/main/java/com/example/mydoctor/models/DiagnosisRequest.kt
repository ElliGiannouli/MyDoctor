package com.example.mydoctor.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DiagnosisRequest(

    val id: String,


    val diagnoses: String,


    val diagnosis_prescription:String,


    val amka_user: String,


    val doctor_email: String
)
