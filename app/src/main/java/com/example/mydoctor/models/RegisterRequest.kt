package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    val amka: String,
    val password: String,
    val First_Name: String,
    val Last_Name : String,
    val Blood_Type : String,
    val Sex : String,
    val email : String,
    val Personal_Doctor : String
)

