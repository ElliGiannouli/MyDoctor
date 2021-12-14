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
import com.example.mydoctor.models.HospitalResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(APP_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitDataHospital = retrofitBuilder.getHospital()

        retrofitDataHospital.enqueue(object:Callback<List<HospitalResponse>?>{
            override fun onResponse(call: Call<List<HospitalResponse>?>, response: Response<List<HospitalResponse>?>) {
                val responseData = response.body()!!

                val hospitalList = mutableListOf<String>()

                for(hospitalResponse in responseData){
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

        autocomplete_text_view_hospital_dropdown.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                doctor_dropdown.isEnabled = true

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                doctor_dropdown.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                var hospital:String = autocomplete_text_view_hospital_dropdown.text.toString().trim()
                Log.d("val hospital","The chosen hospital is: $hospital")

                val retrofitDataDoctors = retrofitBuilder.getDoctors(HospitalRequest(hospital))
                Log.d("retrofitData","the retrofitdatadoctors is $retrofitDataDoctors")

                retrofitDataDoctors.enqueue(object:Callback<DoctorResponse>{
                    override fun onResponse(call: Call<DoctorResponse>, response: Response<DoctorResponse>) {

                        //val responseData = response.body()!!

                        Log.d("responsebody","the response body is : ${response.body()}")

                        val doctorList = mutableListOf<String>()

                        //for(doctorResponse in responseData){
//                            doctorList.add(responseData.doctorName)
//                        //}
//
//                        Log.d("doctorlist","the hospital list is: $doctorList")
//
//                        val adapterDoctors = ArrayAdapter(requireContext(), list_doctors, doctorList)
//                        binding.autocompleteTextViewDoctorDropdown.setAdapter(adapterDoctors)

                    }

                    override fun onFailure(call: Call<DoctorResponse>, t: Throwable) {
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

        val itemsDates = resources.getStringArray(R.array.dates)
        val adapterDate = ArrayAdapter(requireContext(), list_dates, itemsDates)
        binding.autocompleteTextViewDateDropdown.setAdapter(adapterDate)

        val itemsTimes = resources.getStringArray(R.array.times)
        val adapterTime = ArrayAdapter(requireContext(), list_times, itemsTimes)
        binding.autocompleteTextViewTimeDropdown.setAdapter(adapterTime)

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

        super.onViewCreated(view, savedInstanceState)
    }

}
