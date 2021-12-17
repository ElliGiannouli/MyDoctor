package com.example.mydoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mydoctor.api.ApiInterface
import com.example.mydoctor.models.RegisterRequest
import com.example.mydoctor.models.RegisterResponse
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton = registerButton
        var amka =""
        var password = ""
        var firstName = ""
        var lastName = ""
        var bloodType = ""
        var sex = ""
        var email = ""
        var personalDoctor = ""


        registerButton.setOnClickListener{

            amka = amkaRegisterText.text.toString().trim()
            password = passwordRegisterText.text.toString().trim()
            firstName = firstNameRegisterText.text.toString().trim()
            lastName = lastNameRegisterText.text.toString().trim()
            bloodType = bloodTypeRegisterText.text.toString().trim()
            sex = sexRegisterText.text.toString().trim()
            email = emailRegisterText.text.toString().trim()
            personalDoctor = personalDoctorRegisterText.text.toString().trim()


            if (amka.isEmpty()) {
                amkaRegisterText.error = "AMKA required"
                amkaRegisterText.requestFocus()
                return@setOnClickListener
            }
            if (amka.length< 11) {
                amkaRegisterText.error = "AMKA must be 11 numbers"
                amkaRegisterText.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                passwordRegisterText.error = "Password required"
                passwordRegisterText.requestFocus()
                return@setOnClickListener
            }
            if (firstName.isEmpty()) {
                firstNameRegisterText.error = "AMKA required"
                firstNameRegisterText.requestFocus()
                return@setOnClickListener
            }
            if (lastName.isEmpty()) {
                lastNameRegisterText.error = "LastName required"
                lastNameRegisterText.requestFocus()
                return@setOnClickListener
            }
            if (bloodType.isEmpty()) {
                bloodTypeRegisterText.error = "Blood Type required"
                bloodTypeRegisterText.requestFocus()
                return@setOnClickListener
            }
            if (sex.isEmpty()) {
                sexRegisterText.error = "Sex required"
                sexRegisterText.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                emailRegisterText.error = "E-mail required"
                emailRegisterText.requestFocus()
                return@setOnClickListener
            }
            if (personalDoctor.isEmpty()) {
                personalDoctorRegisterText.error = "Personal Doctor required"
                personalDoctorRegisterText.requestFocus()
                return@setOnClickListener
            }



            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)

            val retrofitData = retrofitBuilder.registerUser(RegisterRequest
                (amka,password,firstName, lastName,bloodType,sex,email,personalDoctor))

            retrofitData.enqueue(object: Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    val responseData = response.body()
                    val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                    startActivity(intent)
                    Log.d("SuccessRegister","The response is: $responseData")

                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity,"FailureRegister", Toast.LENGTH_LONG).show()
                    Log.d("registererror","registererror: ${t.localizedMessage} - ${t.stackTrace} - ${t.message}")
                }

            })



        }





    }
}