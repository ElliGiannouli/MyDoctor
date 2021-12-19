package com.example.mydoctor.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PrescriptionsResponse(

    @Expose
    @SerializedName("user_id")
    val user_id: String,

    @Expose
    @SerializedName("amka_user")
    val amka_user: String,

    @Expose
    @SerializedName("description")
    val description: String,

    @Expose
    @SerializedName("email_doc")
    val email_doc: String,

    @Expose
    @SerializedName("fullname_doc")
    val fullname_doc: String

)
