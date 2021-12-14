package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName

data class HospitalRequest (
    @SerializedName("hospital_name")
    val hospitalName: String
        )