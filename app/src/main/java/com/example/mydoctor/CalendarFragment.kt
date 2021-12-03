package com.example.mydoctor

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mydoctor.R.layout.list_dates
import com.example.mydoctor.R.layout.list_times
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlin.text.Typography.times
import android.app.Activity
import android.text.Editable
import android.util.Log
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_calendar.*
import com.example.mydoctor.databinding.FragmentCalendarBinding as FragmentCalendarBinding1
import android.widget.AutoCompleteTextView
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener


class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding1.inflate(layoutInflater)

        val itemsDates = resources.getStringArray(R.array.dates)
        val adapterDate = ArrayAdapter(requireContext(), list_dates, itemsDates)
        binding.autocompleteTextViewDateDropdown.setAdapter(adapterDate)

        val itemsTimes = resources.getStringArray(R.array.times)
        val adapterTime = ArrayAdapter(requireContext(), list_times, itemsTimes)
        binding.autocompleteTextViewTimeDropdown.setAdapter(adapterTime)

//        var selectedValue: String? = null
//
//        (date_dropdown.editText as AutoCompleteTextView).onItemClickListener =
//            OnItemClickListener { _, _, position, _ ->
//                // this code block is called every time an item is clicked
//                selectedValue = adapterDate.getItem(position)
//            }


        binding.bookADateButton.setOnClickListener {

            val chosen_date = autocomplete_text_view_date_dropdown.text.toString()
            val chosen_time = autocomplete_text_view_time_dropdown.text.toString()

            val intent = Intent(requireContext(),ConfirmationActivity::class.java)
            intent.putExtra("date_confirmation", chosen_date)
            intent.putExtra("time_confirmation",chosen_time)
            startActivity(intent)
        }

//        binding.bookADateButton.setOnClickListener() {
//            val fragment: Fragment = CalendarFragment()
//            val fragmentManager = requireActivity().supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.confirmation_fragment, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
//        }

        return binding.root

    }


}
