package com.example.mydoctor

import android.content.Context.MODE_PRIVATE
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
        var firstName = ""
       // var token = arguments?.getString("Token2")
       // Log.d("token", "token is: $token")

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
                   firstNameProfileText.setText(responseData.firstName.toString())
                   lastNameProfileText.setText(responseData.lastName.toString())
                   sexProfileText.setText(responseData.sex.toString())
                   bloodTypeProfileText.setText(responseData.bloodType.toString())
                   personalDoctorProfileText.setText(responseData.personalDoctor.toString())
                   amkaProfileText.setText(responseData.amka.toString())

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

