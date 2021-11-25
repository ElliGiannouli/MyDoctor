package com.example.mydoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mydoctor.R.layout.list_dates
import com.example.mydoctor.R.layout.list_times
import com.example.mydoctor.databinding.FragmentCalendar2Binding
import com.example.mydoctor.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendar2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        binding = FragmentCalendar2Binding.inflate(layoutInflater)

        val itemsDates = listOf("December 13 2021", "December 14 2021", "December 15 2021", "December 16 2021","December 17 2021")
        val adapterDate = ArrayAdapter(requireContext(),list_dates, itemsDates)
        binding.autocompleteTextViewDateDropdown.setAdapter(adapterDate)

        val itemsTimes = listOf("10:00","11:00","12:00","13:00","14:00","17:30","18:30","19:30","20:30")
        val adapterTime = ArrayAdapter(requireContext(),list_times,itemsTimes)
        binding.autocompleteTextViewTimeDropdown.setAdapter(adapterTime)

        return inflater.inflate(R.layout.fragment_calendar_2, container, false)
    }




}