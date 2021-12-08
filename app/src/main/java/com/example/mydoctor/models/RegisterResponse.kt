package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    val user : String ,
    val message : String,
    @SerializedName("Token")
    val token: String

)
