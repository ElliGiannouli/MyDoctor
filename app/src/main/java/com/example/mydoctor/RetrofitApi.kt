package com.example.mydoctor

import android.content.Context
import com.example.mydoctor.api.PrescriptionsApi
import com.example.mydoctor.models.AuthInterceptor
import com.example.mydoctor.models.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit instance class
 */
class ApiClient {
    private lateinit var apiService: PrescriptionsApi

    fun getApiService(context: Context): PrescriptionsApi {

        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.PRE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()

            apiService = retrofit.create(PrescriptionsApi::class.java)
        }

        return apiService
    }

    /**
     * Initialize OkhttpClient with our interceptor
     */
    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

}
