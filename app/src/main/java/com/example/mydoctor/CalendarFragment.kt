package com.example.mydoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.mydoctor.databinding.ActivityMainBinding
import com.example.mydoctor.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        binding.FragmentCalendarBinding.inflate(layoutInflater)

        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter_date = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding.autocompleteTextViewDateDropdown.setAdapter(adapter_date)

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }




}