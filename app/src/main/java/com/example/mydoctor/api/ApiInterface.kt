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

    //@Header("Authorization: Bearer <token>" )

    @GET("doctor")
    fun getDoctors( @Query("hospital_name") hospitalName: String
    ): Call<List<DoctorResponse>>


    @GET("date")
    fun getDates( @Query("email_doc") doctorEmail: String
    ): Call<List<DateResponse>>
}