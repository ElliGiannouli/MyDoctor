package com.example.mydoctor

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import com.example.mydoctor.R.array.*

class DoctorsAdapter (context: Context, resource: Int, private val hospital: LiveData<String>) :
ArrayAdapter<String?>(context, resource) {

    fun updateList(){
        clear()

        val newDoctorList: Array<String?> = when(hospital.value){

            context.resources.getStringArray(hospitals)[0] -> {
                context.resources.getStringArray(doctors_0)
            }
            context.resources.getStringArray(hospitals)[1] -> {
                context.resources.getStringArray(doctors_1)
            }
            context.resources.getStringArray(hospitals)[2] -> {
                context.resources.getStringArray(doctors_2)
            }
//            context.resources.getStringArray(hospitals)[3] -> {
//                context.resources.getStringArray(doctors_3)
//            }
//            context.resources.getStringArray(hospitals)[4] -> {
//                context.resources.getStringArray(doctors_4)
//            }
//            context.resources.getStringArray(hospitals)[5] -> {
//                context.resources.getStringArray(doctors_5)
//            }
//            context.resources.getStringArray(hospitals)[6] -> {
//                context.resources.getStringArray(doctors_6)
//            }
//            context.resources.getStringArray(hospitals)[7] -> {
//                context.resources.getStringArray(doctors_7)
//            }
//            context.resources.getStringArray(hospitals)[8] -> {
//                context.resources.getStringArray(doctors_8)
//            }
//            context.resources.getStringArray(hospitals)[9] -> {
//                context.resources.getStringArray(doctors_9)
//            }
//            context.resources.getStringArray(hospitals)[10] -> {
//                context.resources.getStringArray(doctors_10)
//            }
//            context.resources.getStringArray(hospitals)[11] -> {
//                context.resources.getStringArray(doctors_11)
//            }
//            context.resources.getStringArray(hospitals)[12] -> {
//                context.resources.getStringArray(doctors_12)
//            }
//            context.resources.getStringArray(hospitals)[13] -> {
//                context.resources.getStringArray(doctors_13)
//            }
//            context.resources.getStringArray(hospitals)[14] -> {
//                context.resources.getStringArray(doctors_14)
//            }
//            context.resources.getStringArray(hospitals)[15] -> {
//                context.resources.getStringArray(doctors_15)
//            }
//            context.resources.getStringArray(hospitals)[16] -> {
//                context.resources.getStringArray(doctors_16)
//            }
//            context.resources.getStringArray(hospitals)[17] -> {
//                context.resources.getStringArray(doctors_17)
//            }
//            context.resources.getStringArray(hospitals)[18] -> {
//                context.resources.getStringArray(doctors_18)
//            }
//            context.resources.getStringArray(hospitals)[19] -> {
//                context.resources.getStringArray(doctors_19)
//            }

            else -> arrayOf("Please select a race")
        }
        addAll(newDoctorList.toList())

    }


}