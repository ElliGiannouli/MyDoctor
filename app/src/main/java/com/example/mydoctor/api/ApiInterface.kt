package com.example.mydoctor.api

import com.example.mydoctor.models.*
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET

interface ApiInterface {
    @POST("login")
    fun loginUser(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @GET("hospital")
    fun getHospital(
    ): Call<List<HospitalResponse>>

//    @GET("doctor")
//    fun getDoctors(
//        @Body hospitalRequest: HospitalRequest
//    ): Call<List<DoctorResponse>>

    @GET("doctor")
    fun getDoctors( @Query("hospital_name" ) hospitalName: HospitalRequest
    ): Call<DoctorResponse>

    @GET("date")
    fun getDates(
        @Body doctorRequest: DoctorRequest
    ): Call<List<DateResponse>>
}