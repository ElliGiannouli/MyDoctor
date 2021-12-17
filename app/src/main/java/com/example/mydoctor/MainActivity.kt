package com.example.mydoctor

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpTabBar()
    }

    private fun setUpTabBar() {
        var token = intent.getStringExtra("Token")
        val adapter = TabPageAdapter(activity = this , tabLayout.tabCount ,token.toString())
        viewPager.adapter = adapter
        Log.d("token", "token is in main: $token")
        val sharedPrefsToken: SharedPreferences = this.getPreferences(MODE_PRIVATE)
        val editor = sharedPrefsToken.edit()
        editor.putString("sharedprefstoken", token.toString())
        editor.apply()

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }






        })
    }
}