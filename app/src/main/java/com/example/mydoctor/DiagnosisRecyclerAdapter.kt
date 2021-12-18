package com.example.mydoctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoctor.models.DiagnosisResponse


class DiagnosisRecyclerAdapter(private val list: MutableList<DiagnosisResponse>): RecyclerView.Adapter<DiagnosisRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemKode: TextView = itemView.findViewById(R.id.kode)
        fun bindItem(item:DiagnosisResponse){

            itemKode.text=item.id
            itemKode.text=item.diagnoses
            itemKode.text=item.diagnosis_prescription
            itemKode.text=item.amka_user
            itemKode.text=item.doctor_email
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {

        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cardview_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bindItem(list[i])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}




