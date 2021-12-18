package com.example.mydoctor.api



import com.example.mydoctor.models.PrescriptionsResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Header


interface PrescriptionsApi {



    @GET(com.example.mydoctor.models.Constants.PRE_URL)
    fun fetchPosts(@Header("Authorization") token: String): Call<PrescriptionsResponse>

    @GET("Prescriptions/")
    fun getPrescriptionsList(): Call<MutableList<PrescriptionsResponse?>?>?


}