package com.example.mydoctor

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.mydoctor.api.ApiInterface
import com.example.mydoctor.models.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.list_times.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL2  = "https://docappmy.herokuapp.com/mydoctor/"

class ProfileFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,


        ): View? {

        // token from the log in

        val sharedPrefsToken2: SharedPreferences = requireActivity().getPreferences(MODE_PRIVATE)
        val token2 = sharedPrefsToken2.getString("sharedprefstoken"," ").toString()
        Log.d("token2", "token2 is: $token2")



        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL2)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getUser(token2)

        retrofitData.enqueue(object : Callback<UserMessage> {
            override fun onResponse(call: Call<UserMessage>, response: Response<UserMessage>) {
                if (response.isSuccessful){
                    val responseData = response.body()!!
                    nameProfileText.setText(responseData.firstName.toString() + " " + responseData.lastName.toString())
                    sexProfileText.setText("Gender: \n" +responseData.sex.toString())
                    bloodTypeProfileText.setText("Blood Type: \n" +responseData.bloodType.toString())
                    personalDoctorProfileText.setText("Personal Doctor: \n" +responseData.personalDoctor.toString())
                    amkaProfileText.setText("AMKA: \n" +responseData.amka.toString())
                    emailProfileText.setText("E-mail: \n" +responseData.email.toString())

                    Log.d("Bravo","token einai edw : $token2")
                }
            }

            override fun onFailure(call: Call<UserMessage>, t: Throwable) {
                Log.e("Error:::","Error "+t!!.message)
            }
        })



        return inflater.inflate(R.layout.fragment_profile, container, false)

    }
}

