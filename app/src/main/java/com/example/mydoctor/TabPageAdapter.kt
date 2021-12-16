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
            1 -> CalendarFragment()
            2 -> UploadFragment()
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