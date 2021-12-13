package com.example.mydoctor.api

import com.example.mydoctor.models.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("login")
    fun loginUser(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>
    @GET("hospital")
    fun getHospital(
    ): Call<List<HospitalResponse>>
    @POST("doctor")
    fun getDoctors(
        @Body hospitalRequest: HospitalRequest
    ): Call<List<DoctorResponse>>
    @POST("date")
    fun getDates(
        @Body doctorRequest: DoctorRequest
    ): Call<List<DateResponse>>
}