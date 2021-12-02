package com.example.mydoctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val value = intent.getStringExtra("Confirmation").toString()
        Log.d("conf","Values is: ${value}")
    }
}