package com.example.mydoctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoctor.models.PrescriptionsResponse

class PrescriptionsRecyclerAdapter(private val list: MutableList<PrescriptionsResponse>): RecyclerView.Adapter<PrescriptionsRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemKode: TextView = itemView.findViewById(R.id.kode)
        fun bindItem(item:PrescriptionsResponse){
            itemKode.text=item.id
            itemKode.text=item.description
            itemKode.text=item.amka_user
            itemKode.text=item.email_doc
            itemKode.text=item.fullname_doc
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




