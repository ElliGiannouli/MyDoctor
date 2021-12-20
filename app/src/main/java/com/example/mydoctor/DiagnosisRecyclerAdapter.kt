package com.example.mydoctor

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoctor.models.DiagnosisResponse
import kotlinx.android.synthetic.main.cardview_layout_diagnosis.view.*


class DiagnosisRecyclerAdapter(val context: Context, private val diagnosisList:List<DiagnosisResponse>): RecyclerView.Adapter<DiagnosisRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var diagnosis: TextView
        var diagnosis_description: TextView
        var doctor_email: TextView

        init {

            diagnosis = itemView.text_view_diagnosis
            diagnosis_description = itemView.text_view_diagnosis_description
            doctor_email = itemView.text_view_doctor_email

        }

        fun bindItem (item: DiagnosisResponse){

            diagnosis.text=item.diagnosis
            diagnosis_description.text=item.diagnosis_description
            doctor_email.text=item.doctor_email

            Log.d("onholderdiagnosis","on holderdiagnosis item $item")

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout_diagnosis, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        holder.diagnosis.text= diagnosisList[position].diagnosis
//        holder.diagnosis_description.text = diagnosisList[position].diagnosis_description
//        holder.doctor_email.text = diagnosisList[position].doctor_email

        holder.bindItem(diagnosisList[position])
    }

    override fun getItemCount(): Int {
        return diagnosisList.size
    }
}