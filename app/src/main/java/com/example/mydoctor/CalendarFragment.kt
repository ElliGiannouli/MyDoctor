package com.example.mydoctor

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.mydoctor.databinding.FragmentCalendarBinding
import com.example.mydoctor.models.*

const val APP_URL = "https://docappmy.herokuapp.com/mydoctor/appointments/"

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val datesList = listOf(
            "12/13/2021 10:00","12/13/2021 11:00","12/13/2021 12:00","12/13/2021 13:00","12/13/2021 14:00","12/13/2021 17:30","12/13/2021 18:30","12/13/2021 19:30","12/13/2021 20:30",
            "12/14/2021 10:00","12/14/2021 11:00","12/14/2021 12:00","12/14/2021 13:00","12/14/2021 14:00","12/14/2021 17:30","12/14/2021 18:30","12/14/2021 19:30","12/14/2021 20:30",
            "12/15/2021 10:00","12/15/2021 11:00","12/15/2021 12:00","12/15/2021 13:00","12/15/2021 14:00","12/15/2021 17:30","12/15/2021 18:30","12/15/2021 19:30","12/15/2021 20:30",
            "12/16/2021 10:00","12/16/2021 11:00","12/16/2021 12:00","12/16/2021 13:00","12/16/2021 14:00","12/16/2021 17:30","12/16/2021 18:30","12/16/2021 19:30","12/16/2021 20:30",
            "12/17/2021 10:00","12/17/2021 11:00","12/17/2021 12:00","12/17/2021 13:00","12/17/2021 14:00","12/17/2021 17:30","12/17/2021 18:30","12/17/2021 19:30","12/17/2021 20:30")

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(APP_URL)
            .build()
            .create(ApiInterface::class.java)

        //HOSPITAL DROPDOWN STARTS HERE

        val retrofitDataHospital = retrofitBuilder.getHospital()

        retrofitDataHospital.enqueue(object:Callback<List<HospitalResponse>?>{
            override fun onResponse(call: Call<List<HospitalResponse>?>, response: Response<List<HospitalResponse>?>) {
                val responseDataHospitals = response.body()!!

                val hospitalList = mutableListOf<String>()

                for(hospitalResponse in responseDataHospitals){
                    hospitalList.add(hospitalResponse.hospitalName)
                }

                Log.d("hospitallist","the hospital list is: $hospitalList")

                val adapterHospitals = ArrayAdapter(requireContext(),list_hospitals,hospitalList)
                binding.autocompleteTextViewHospitalDropdown.setAdapter(adapterHospitals)

            }

            override fun onFailure(call: Call<List<HospitalResponse>?>, t: Throwable) {
                Log.d("ERRORhospitals","Failed at hospitals:"+t.message)
            }
        })

        //DOCTOR DROPDOWN STARTS HERE

        autocomplete_text_view_hospital_dropdown.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                doctor_dropdown.isEnabled = true

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                doctor_dropdown.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val hospital:String = autocomplete_text_view_hospital_dropdown.text.toString().trim()
                Log.d("val hospital","The chosen hospital is: $hospital")

                val retrofitDataDoctors = retrofitBuilder.getDoctors(hospital)
                Log.d("retrofitData","the retrofitdatadoctors is $retrofitDataDoctors")

                retrofitDataDoctors.enqueue(object:Callback<List<DoctorResponse>>{
                    override fun onResponse(call: Call<List<DoctorResponse>>, response: Response<List<DoctorResponse>>) {

                        val responseDataDoctors = response.body()!!

                        Log.d("responsebody","the response body is : ${response.body()}")

                        val doctorList = mutableListOf<String>()
                        val emailList = mutableListOf<String>()

                        for(doctorResponse in responseDataDoctors){
                            doctorList.add(doctorResponse.doctorName)
                            emailList.add(doctorResponse.doctorEmail)
                        }

                        Log.d("doctorlist","the doctor list is: $doctorList")

                        val adapterDoctors = ArrayAdapter(requireContext(), list_doctors, doctorList)
                        binding.autocompleteTextViewDoctorDropdown.setAdapter(adapterDoctors)

                        //DATE DROPDOWN STARTS HERE

                        autocomplete_text_view_doctor_dropdown.addTextChangedListener(object: TextWatcher{
                            override fun afterTextChanged(s: Editable?) {

                                date_and_time_dropdown.isEnabled = true
                            }

                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                                date_and_time_dropdown.isEnabled = false
                            }

                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                                val doctor:String = autocomplete_text_view_doctor_dropdown.text.toString().trim()
                                Log.d("val doctor","The chosen doctor is: $doctor")
                                var i = 0
                                var doctorEmail = ""
                                while(i <doctorList.size) {
                                    if (doctorList[i] == doctor) {
                                        doctorEmail = emailList[i]
                                    }
                                    i++
                                }

                                Log.d("emaildoctor","The doctor's email is : $doctorEmail")

                                val retrofitDataDates = retrofitBuilder.getDates(doctorEmail)
                                Log.d("retrofitData","the retrofitdatadates is $retrofitDataDates")

                                retrofitDataDates.enqueue(object:Callback<List<DateAndTimeResponse>?>{
                                    override fun onResponse(call: Call<List<DateAndTimeResponse>?>, response: Response<List<DateAndTimeResponse>?>) {

                                        val responseDataDates = response.body()!!

                                        Log.d("responsebody","the response body of dates is : ${response.body()}")

                                        val unavailableDatesList = mutableListOf<String>()

                                        for(dateResponse in responseDataDates){
                                            unavailableDatesList.add(dateResponse.notAvailableDates)
                                        }

                                        val intermediateDatesList = datesList.toMutableList()
                                        intermediateDatesList.removeAll(unavailableDatesList)
                                        val availableDatesList = mutableListOf<String>()
                                        availableDatesList.addAll(intermediateDatesList)

                                        val adapterDates = ArrayAdapter(requireContext(), list_dates, availableDatesList)
                                        binding.autocompleteTextViewDateAndTimeDropdown.setAdapter(adapterDates)

                                        binding.bookADateButton.setOnClickListener {

                                            val dateAndTime: String = autocomplete_text_view_date_and_time_dropdown.text.toString().trim()

                                            val retrofitDataBookedAppointment = retrofitBuilder.sendAppointment(sendAppointmentRequest(,doctorEmail,dateAndTime))

                                            val chosenHospital = autocomplete_text_view_hospital_dropdown.text.toString()
                                            val chosenDoctor = autocomplete_text_view_doctor_dropdown.text.toString()
                                            val chosenDateAndTime = autocomplete_text_view_date_and_time_dropdown.text.toString()

                                            val intent = Intent(requireContext(),ConfirmationActivity::class.java)
                                            intent.putExtra("hospital_confirmation",chosenHospital)
                                            intent.putExtra("doctor_confirmation",chosenDoctor)
                                            intent.putExtra("date_and_time_confirmation", chosenDateAndTime)
                                            startActivity(intent)
                                        }

                                    }

                                    override fun onFailure(call: Call<List<DateAndTimeResponse>?>, t: Throwable) {
                                        Log.d("ERRORdates","Failed at dates:"+t.message)
                                    }
                                })
                            }

                        })


                    }

                    override fun onFailure(call: Call<List<DoctorResponse>>, t: Throwable) {
                        Log.d("ERRORdoctors","Failed at doctors:"+t.message)
                    }
                })
            }
        })

//        val itemsHospitals = resources.getStringArray(R.array.hospitals)
//        val adapterHospitals = ArrayAdapter(requireContext(),list_hospitals,itemsHospitals)
//        binding.autocompleteTextViewHospitalDropdown.setAdapter(adapterHospitals)

//        val itemsDoctors = resources.getStringArray(R.array.doctors_0)
//        val adapterDoctors = ArrayAdapter(requireContext(), list_doctors, itemsDoctors)
//        binding.autocompleteTextViewDoctorDropdown.setAdapter(adapterDoctors)

//        val itemsDates = resources.getStringArray(R.array.dates)
//        val adapterDate = ArrayAdapter(requireContext(), list_dates, datesList)
//        binding.autocompleteTextViewDateAndTimeDropdown.setAdapter(adapterDate)

//        autocomplete_text_view_doctor_dropdown.addTextChangedListener(object: TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//
//                date_and_time_dropdown.isEnabled = true
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//                date_and_time_dropdown.isEnabled = false
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//                val doctor:String = autocomplete_text_view_doctor_dropdown.text.toString().trim()
//                Log.d("val doctor","The chosen doctor is: $doctor")
//
//                val retrofitDataDates = retrofitBuilder.getDates(doctor)
//                Log.d("retrofitData","the retrofitdatadates is $retrofitDataDates")
//
//                retrofitDataDates.enqueue(object:Callback<List<DateResponse>>{
//                    override fun onResponse(call: Call<List<DateResponse>>, response: Response<List<DateResponse>>) {
//
//                        val responseData = response.body()!!
//
//                        Log.d("responsebody","the response body is : ${response.body()}")
//
//                        val unavailableDatesList = mutableListOf<String>()
//
//                        for(dateResponse in responseData){
//                            unavailableDatesList.add(dateResponse.notAvailableDates)
//                        }
//
//                        val intermediateDatesList = datesList.toMutableList()
//                        intermediateDatesList.removeAll(unavailableDatesList)
//                        val availableDatesList = mutableListOf<String>()
//                        availableDatesList.addAll(intermediateDatesList)
//
//                        val adapterDates = ArrayAdapter(requireContext(), list_dates, availableDatesList)
//                        binding.autocompleteTextViewDateAndTimeDropdown.setAdapter(adapterDates)
//
//                    }
//
//                    override fun onFailure(call: Call<List<DateResponse>>, t: Throwable) {
//                        Log.d("ERRORdates","Failed at dates:"+t.message)
//                    }
//                })
//            }
//
//        })

        binding.bookADateButton.setOnClickListener {

            val chosenHospital = autocomplete_text_view_hospital_dropdown.text.toString()
            val chosenDoctor = autocomplete_text_view_doctor_dropdown.text.toString()
            val chosenDateAndTime = autocomplete_text_view_date_and_time_dropdown.text.toString()

            val intent = Intent(requireContext(),ConfirmationActivity::class.java)
            intent.putExtra("hospital_confirmation",chosenHospital)
            intent.putExtra("doctor_confirmation",chosenDoctor)
            intent.putExtra("date_and_time_confirmation", chosenDateAndTime)
            startActivity(intent)
        }

        super.onViewCreated(view, savedInstanceState)
    }

}
