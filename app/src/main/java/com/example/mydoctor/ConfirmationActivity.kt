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

        val bookedDoctorView : TextView = booked_doctor_view as TextView
        val bookedDateView: TextView = booked_date_view as TextView
        val bookedTimeView: TextView = booked_time_view as TextView

        val bookeddoctor = intent.getStringExtra("doctor_confirmation").toString()
        val bookeddate = intent.getStringExtra("date_confirmation").toString()
        val bookedtime = intent.getStringExtra("time_confirmation").toString()

        bookedDoctorView.text = "Doctor $bookeddoctor"
        bookedDateView.text = "on $bookeddate"
        bookedTimeView.text = "at $bookedtime"
    }
}