package com.example.mydoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.mydoctor.api.ApiInterface
import com.example.mydoctor.models.LoginRequest
import com.example.mydoctor.models.LoginResponse
import kotlinx.android.synthetic.main.activity_loig_in.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://docappmy.herokuapp.com/mydoctor/user/"

class LoigInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loig_in)

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

        //val amkatextview = amka.text.toString().trim()
        //val passwordtextview = password.text.toString().trim()
        val loginbutton = loginbtm
        var amka1 = ""
        var password1 = ""

        loginbutton.setOnClickListener {

            amka1 = amka_edit_text.text.toString().trim()
            password1 = password_edit_text.text.toString().trim()

            Log.d("values","The AMKA is: $amka1, The password is: $password1")



        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.loginUser(LoginRequest(amka1,password1))

        retrofitData.enqueue(object:Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val responseData = response.body()
                Log.d("SuccessLogin","The response is: $responseData")

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoigInActivity,"FailureLogin",Toast.LENGTH_LONG).show()
                Log.d("loginerror","loginerror: ${t.localizedMessage} - ${t.stackTrace} - ${t.message}")
            }

        })


    }
    }
}