package com.example.mydoctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_confirmation.*


class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val bookedDateView: TextView = booked_date_view as TextView
        val bookedTimeView: TextView = booked_time_view as TextView

        val booked_date = intent.getStringExtra("date_confirmation").toString()
        val booked_time = intent.getStringExtra("time_confirmation").toString()

        bookedDateView.text = "on $booked_date"
        bookedTimeView.text = "at $booked_time"
    }
}