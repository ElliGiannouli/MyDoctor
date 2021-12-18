package com.example.mydoctor.api



import com.example.mydoctor.models.DiagnosisResponse
import com.example.mydoctor.models.PrescriptionsResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Header


interface DiagnosisApi {

    @GET(com.example.mydoctor.models.Constants.DIA_URL)
    fun fetchPosts(@Header("Authorization") token: String): Call<DiagnosisResponse>




        @GET("Diagnoses/")
        fun getDiagnosisList(): Call<MutableList<DiagnosisResponse>>


    }


