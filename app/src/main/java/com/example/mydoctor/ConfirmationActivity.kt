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

        val bookedHospitalView : TextView = booked_hospital_view as TextView
        val bookedDoctorView : TextView = booked_doctor_view as TextView
        val bookedDateAndTimeView : TextView = booked_date_and_time_view as TextView

        val bookedHospital = intent.getStringExtra("hospital_confirmation").toString()
        val bookedDoctor = intent.getStringExtra("doctor_confirmation").toString()
        val bookedDateAndTime = intent.getStringExtra("date_and_time_confirmation").toString()

        bookedHospitalView.text = "at $bookedHospital"
        bookedDoctorView.text = "with Doctor $bookedDoctor"
        bookedDateAndTimeView.text = "on $bookedDateAndTime"
    }
}