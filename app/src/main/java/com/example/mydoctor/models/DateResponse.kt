package com.example.mydoctor.models

import com.google.gson.annotations.SerializedName
import org.json.JSONArray

data class DateResponse(

    @SerializedName("")
    val notAvailableDates: JSONArray

)
