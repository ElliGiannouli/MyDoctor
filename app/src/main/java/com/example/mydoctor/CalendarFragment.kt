package com.example.mydoctor

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





class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

//        binding.bookADateButton = activity!!.supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_confirmation, ProfileFragment()).commit()

//        val bookDateButton = binding.bookADateButton as TextView
//        bookDateButton.setOnClickListener(){
//            val intent = Intent(this, ConfirmationFragment::class.java);
//            startActivity(intent)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        val itemsDates = resources.getStringArray(R.array.dates)
        val adapterDate = ArrayAdapter(requireContext(),list_dates, itemsDates)
        binding.autocompleteTextViewDateDropdown.setAdapter(adapterDate)

        val itemsTimes = resources.getStringArray(R.array.times)
        val adapterTime = ArrayAdapter(requireContext(),list_times,itemsTimes)
        binding.autocompleteTextViewTimeDropdown.setAdapter(adapterTime)

        return binding.root

    }




}