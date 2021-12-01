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
import com.example.mydoctor.databinding.FragmentCalendarBinding
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlin.text.Typography.times
import android.app.Activity
import android.util.Log
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_calendar.*






class CalendarFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        val itemsDates = resources.getStringArray(R.array.dates)
        val adapterDate = ArrayAdapter(requireContext(), list_dates, itemsDates)
        binding.autocompleteTextViewDateDropdown.setAdapter(adapterDate)

        val itemsTimes = resources.getStringArray(R.array.times)
        val adapterTime = ArrayAdapter(requireContext(), list_times, itemsTimes)
        binding.autocompleteTextViewTimeDropdown.setAdapter(adapterTime)

        binding.bookADateButton.setOnClickListener() {
            val fragment: Fragment = CalendarFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.confirmation_fragment, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return binding.root

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}
