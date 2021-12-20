package com.example.mydoctor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoctor.models.PrescriptionsResponse
import kotlinx.android.synthetic.main.cardview_layout_prescriptions.view.*


class PrescriptionsRecyclerAdapter(val context: Context, val prescriptionsList:List<PrescriptionsResponse>): RecyclerView.Adapter<PrescriptionsRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var prescriptions: TextView
        var prescriptions_description: TextView
        var doctor_email: TextView

        init {

            prescriptions = itemView.text_view_prescriptions
            prescriptions_description = itemView.text_view_prescriptions_description
            doctor_email = itemView.text_view_doctor_email

        }

//        var itemKode: TextView = itemView.findViewById(R.id.text_view_diagnosis)
//        fun bindItem(item:DiagnosisResponse){
//
//            itemKode.text=item.id
//            itemKode.text=item.diagnosis
//            itemKode.text=item.diagnosis_description
//            itemKode.text=item.amka_user
//            itemKode.text=item.doctor_email
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PrescriptionsRecyclerAdapter.ViewHolder {

        var itemView = LayoutInflater.from(context).inflate(R.layout.cardview_layout_prescriptions,parent,false)
        return ViewHolder(itemView)
//        val v = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.cardview_layout, viewGroup, false)
//        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.prescriptions.text= prescriptionsList[position].prescriptions.toString()
        holder.prescriptions_description.text = prescriptionsList[position].prescriptions_description.toString()
        holder.doctor_email.text = prescriptionsList[position].doctor_email.toString()


//        viewHolder.bindItem(diagnosisList[i])
    }

    override fun getItemCount(): Int {
        return prescriptionsList.size
    }
}