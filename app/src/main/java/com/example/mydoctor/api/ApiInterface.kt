package com.example.mydoctor.api


import com.example.mydoctor.models.LoginRequest
import com.example.mydoctor.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST



interface ApiInterface {
    @POST("login")
    fun loginUser(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>


}