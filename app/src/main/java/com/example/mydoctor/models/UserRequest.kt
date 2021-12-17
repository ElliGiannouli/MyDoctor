package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("Token")
    val token: String
)