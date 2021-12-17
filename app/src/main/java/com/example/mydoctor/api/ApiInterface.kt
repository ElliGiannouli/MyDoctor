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

    @POST("register")
    fun registerUser(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>

    @GET("home")
    fun getUser(
        @Query ("token") token : String //query
    ): Call<UserMessage>

    //@Header("Authorization: Bearer <token>" )

    @GET("doctor")
    fun getDoctors( @Query("hospital_name") hospitalName: String
    ): Call<List<DoctorResponse>>

    @GET("date")
    fun getDates( @Query("email_doc") doctorEmail: String
    ): Call<List<DateAndTimeResponse>>

    @POST("appointment/{token_id}")
    fun sendAppointment (
        @Path("token_id") tokenId:String,
        @Body sendAppointmentRequest: SendAppointmentRequest
    ): Call<BookedAppointmentResponse>
}