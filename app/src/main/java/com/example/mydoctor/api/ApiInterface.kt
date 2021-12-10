package com.example.mydoctor.api

import com.example.mydoctor.models.HospitalRequest
import com.example.mydoctor.models.HospitalResponse
import com.example.mydoctor.models.LoginRequest
import com.example.mydoctor.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("login")
    fun loginUser(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>
    @GET("hospitals")
    fun getHospital(
        @Body hospitalRequest: HospitalRequest
    ): Call<HospitalResponse>

}