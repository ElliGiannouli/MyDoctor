package com.example.mydoctor

import android.accounts.AccountManager
import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.mydoctor.api.ApiInterface
import com.example.mydoctor.models.LoginRequest
import com.example.mydoctor.models.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://docappmy.herokuapp.com/mydoctor/user/"

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val clickRegister = findViewById<TextView>(R.id.clickRegister)
        clickRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)

        }

//        val loginbtm = findViewById<TextView>(R.id.loginbtm)
//        loginbtm.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java);
//            startActivity(intent)
//
//        }

        val loginbutton = loginbtm
        var amka = ""
        var password = ""

        loginbutton.setOnClickListener {

            amka = amka_edit_text.text.toString().trim()
            password = password_edit_text.text.toString().trim()

            Log.d("values","The AMKA is: $amka, The password is: $password")



        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.loginUser(LoginRequest(amka,password))

        retrofitData.enqueue(object:Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val responseData = response.body()
                Log.d("SuccessLogin","The response is: $responseData")
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity,"FailureLogin",Toast.LENGTH_LONG).show()
                Log.d("loginerror","loginerror: ${t.localizedMessage} - ${t.stackTrace} - ${t.message}")
            }

        })


    }

    }
}