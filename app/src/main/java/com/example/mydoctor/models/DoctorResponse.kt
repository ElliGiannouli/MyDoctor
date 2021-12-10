package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName

data class DoctorResponse(

    @SerializedName("fullname_doc")
    val doctorName: String,

    @SerializedName("medic_spec")
    val doctorSpec: String

)
