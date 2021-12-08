package com.example.mydoctor.models


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("Token")
    val token: String
)