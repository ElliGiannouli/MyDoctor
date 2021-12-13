package com.example.mydoctor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_calendar.*
import com.example.mydoctor.R.layout.*
import com.example.mydoctor.api.ApiInterface
import com.example.mydoctor.models.HospitalResponse
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.ArrayList
import com.example.mydoctor.databinding.FragmentCalendarBinding
import com.example.mydoctor.models.DoctorResponse
import com.example.mydoctor.models.HospitalRequest

const val APP_URL = "https://docappmy.herokuapp.com/mydoctor/appointments/"

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(APP_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitDataHospital = retrofitBuilder.getHospital()

        retrofitDataHospital.enqueue(object:Callback<List<HospitalResponse>?>{
            override fun onResponse(call: Call<List<HospitalResponse>?>, response: Response<List<HospitalResponse>?>) {
                val responseData = response.body()!!

                val hospital_list = mutableListOf<String>()

                for(hospitalResponse in responseData){
                    hospital_list.add(hospitalResponse.hospitalName)
                }

                Log.d("hospitallist","the hospital list is: $hospital_list")

                val adapterHospitals = ArrayAdapter(requireContext(),list_hospitals,hospital_list)
                binding.autocompleteTextViewHospitalDropdown.setAdapter(adapterHospitals)

            }

            override fun onFailure(call: Call<List<HospitalResponse>?>, t: Throwable) {
                Log.d("ERRORhospitals","Failed at hospitals:"+t.message)
            }
        })
        
        val hospital = autocomplete_text_view_hospital_dropdown.text.toString().trim()

        val retrofitDataDoctors = retrofitBuilder.getDoctors(HospitalRequest(hospital))

        retrofitDataDoctors.enqueue(object:Callback<List<DoctorResponse>?>{
            override fun onResponse(call: Call<List<DoctorResponse>?>, response: Response<List<DoctorResponse>?>) {

                val responseData = response.body()!!

                val doctor_list = mutableListOf<String>()

                for(doctorResponse in responseData){
                    doctor_list.add(doctorResponse.doctorName)
                }

                Log.d("doctorlist","the hospital list is: $doctor_list")

                val adapterDoctors = ArrayAdapter(requireContext(), list_doctors, doctor_list)
                binding.autocompleteTextViewDoctorDropdown.setAdapter(adapterDoctors)

            }

            override fun onFailure(call: Call<List<DoctorResponse>?>, t: Throwable) {
                Log.d("ERRORdoctors","Failed at doctors:"+t.message)
            }
        })

//        val itemsHospitals = resources.getStringArray(R.array.hospitals)
//        val adapterHospitals = ArrayAdapter(requireContext(),list_hospitals,itemsHospitals)
//        binding.autocompleteTextViewHospitalDropdown.setAdapter(adapterHospitals)

//        val itemsDoctors = resources.getStringArray(R.array.doctors_0)
//        val adapterDoctors = ArrayAdapter(requireContext(), list_doctors, itemsDoctors)
//        binding.autocompleteTextViewDoctorDropdown.setAdapter(adapterDoctors)

        val itemsDates = resources.getStringArray(R.array.dates)
        val adapterDate = ArrayAdapter(requireContext(), list_dates, itemsDates)
        binding.autocompleteTextViewDateDropdown.setAdapter(adapterDate)

        val itemsTimes = resources.getStringArray(R.array.times)
        val adapterTime = ArrayAdapter(requireContext(), list_times, itemsTimes)
        binding.autocompleteTextViewTimeDropdown.setAdapter(adapterTime)

        binding.bookADateButton.setOnClickListener {

            val chosenHospital = autocomplete_text_view_hospital_dropdown.text.toString()
            val chosenDoctor = autocomplete_text_view_doctor_dropdown.text.toString()
            val chosenDate = autocomplete_text_view_date_dropdown.text.toString()
            val chosenTime = autocomplete_text_view_time_dropdown.text.toString()

            val intent = Intent(requireContext(),ConfirmationActivity::class.java)
            intent.putExtra("hospital_confirmation",chosenHospital)
            intent.putExtra("doctor_confirmation",chosenDoctor)
            intent.putExtra("date_confirmation", chosenDate)
            intent.putExtra("time_confirmation",chosenTime)
            startActivity(intent)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

     autocomplete_text_view_hospital_dropdown.addTextChangedListener(object: TextWatcher{
          override fun afterTextChanged(s: Editable?) {

              doctor_dropdown.isEnabled = true
          }

          override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

              doctor_dropdown.isEnabled = false
          }

          override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
          }

      })

        autocomplete_text_view_doctor_dropdown.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                date_dropdown.isEnabled = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                date_dropdown.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        autocomplete_text_view_date_dropdown.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                time_dropdown.isEnabled = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                time_dropdown.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        super.onViewCreated(view, savedInstanceState)
    }

}
