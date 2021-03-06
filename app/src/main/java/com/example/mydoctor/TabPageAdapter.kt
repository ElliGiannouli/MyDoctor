package com.example.mydoctor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPageAdapter (activity:FragmentActivity, private val tabCount: Int ,private val token:String): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int =tabCount

    override fun createFragment(position: Int): Fragment
    {

        return when (position){

            0 -> {
                val fragment = ProfileFragment()
                val bundle = Bundle()
                bundle.putString("Token2",token)
                Log.d("token", "token is in adapter: $token")
                fragment.arguments = bundle
                ProfileFragment()
            }
            1 -> {
                val calendarFragment = CalendarFragment()
                val calendarBundle = Bundle()
                calendarBundle.putString("tokenCalendar",token)
                Log.d("calendartoken","token is in adapter in calendar fragment : $token")
                calendarFragment.arguments = calendarBundle
                CalendarFragment()
                }
            2 -> {
                val diagnosisFragment = DiagnosisFragment()
                val diagnosisBundle = Bundle()
                diagnosisBundle.putString("tokenDiagnosis", token)
                Log.d("diagnosistoken", "token is in adapter in diagnosis fragment : $token")
                diagnosisFragment.arguments = diagnosisBundle
                DiagnosisFragment()
                }
            3 -> {
                val prescriptionsFragment = PrescriptionsFragment()
                val prescriptionsBundle = Bundle()
                prescriptionsBundle.putString("tokenPrescriptions", token)
                Log.d("diagnosistoken", "token is in adapter in diagnosis fragment : $token")
                prescriptionsFragment.arguments = prescriptionsBundle
                PrescriptionsFragment()
                }
            else -> {
                val fragment = ProfileFragment()
                val args = Bundle()
                args.putString("Token2",token)
                Log.d("token", "token is in adapter: $token")
                fragment.arguments = args
                ProfileFragment()
            }
        }

    }
}