package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("message")
    val message: UserMessage
)