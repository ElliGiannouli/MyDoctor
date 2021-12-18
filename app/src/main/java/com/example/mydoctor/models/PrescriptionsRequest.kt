package com.example.mydoctor.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PrescriptionsRequest(

    val id: String,


    val amka_user: String,


    val description: String,


    val email_doc: String,


    val fullname_doc: String
)
