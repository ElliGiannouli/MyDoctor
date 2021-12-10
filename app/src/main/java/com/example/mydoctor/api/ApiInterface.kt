package com.example.mydoctor.api

import com.example.mydoctor.models.*
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
    ): Call<List<HospitalResponse>>
    @POST("doctors")
    fun getDoctors(
        @Body hospitalRequest: HospitalRequest
    ): Call<List<DoctorResponse>>
    @POST("dates")
    fun getDates(
        @Body doctorRequest: DoctorRequest
    ): Call<List<DateResponse>>
}