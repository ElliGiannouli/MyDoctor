package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName

data class UserMessage(
    @SerializedName("--v")
    val v: String,
    @SerializedName("Blood_Type")
    val bloodType: String,
    @SerializedName("First_Name")
    val firstName: String,
    @SerializedName("Last_Name")
    val lastName: String,
    @SerializedName("Personal_Doctor")
    val personalDoctor: String,
    @SerializedName("Photo")
    val photo: String,
    @SerializedName("Sex")
    val sex: String,
    @SerializedName("amka")
    val amka: String,
    @SerializedName("date")
    val date: String,
    val email: String,
    @SerializedName("id")
    val id: String
)