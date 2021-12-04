package com.example.mydoctor

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_calendar.*
import com.example.mydoctor.R.layout.*
import com.example.mydoctor.databinding.FragmentCalendarBinding


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

        val itemsDoctors = resources.getStringArray(R.array.doctors)
        val adapterDoctors = ArrayAdapter(requireContext(), list_doctors, itemsDoctors)
        binding.autocompleteTextViewDoctorDropdown.setAdapter(adapterDoctors)

        val itemsDates = resources.getStringArray(R.array.dates)
        val adapterDate = ArrayAdapter(requireContext(), list_dates, itemsDates)
        binding.autocompleteTextViewDateDropdown.setAdapter(adapterDate)

        val itemsTimes = resources.getStringArray(R.array.times)
        val adapterTime = ArrayAdapter(requireContext(), list_times, itemsTimes)
        binding.autocompleteTextViewTimeDropdown.setAdapter(adapterTime)

        binding.bookADateButton.setOnClickListener {

            val chosenDoctor = autocomplete_text_view_doctor_dropdown.text.toString()
            val chosenDate = autocomplete_text_view_date_dropdown.text.toString()
            val chosenTime = autocomplete_text_view_time_dropdown.text.toString()

            val intent = Intent(requireContext(),ConfirmationActivity::class.java)
            intent.putExtra("doctor_confirmation",chosenDoctor)
            intent.putExtra("date_confirmation", chosenDate)
            intent.putExtra("time_confirmation",chosenTime)
            startActivity(intent)
        }

        return binding.root

    }


}
