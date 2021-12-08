package com.example.mydoctor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val kode = arrayOf(
        "d116df5",
        "36ffc75", "f5cfe78", "5b87628",
        "db8d14e", "9913dc4", "e120f96",
        "466251b"
    )

    private val kategori = arrayOf(

        "1", "2", "3", "4", "5", "6", "7", "8"
    )

    private val isi = arrayOf(
        "9",
        "11", "17", "58",
        "12", "18", "20",
        "21"
    )


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemKode: TextView = itemView.findViewById(R.id.kode)
        var itemKategori: TextView = itemView.findViewById(R.id.kategor)
        var itemIsi: TextView = itemView.findViewById(R.id.isi)

        init {

            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, DiagnosesFragment::class.java).apply {
                    putExtra("NUMBER", position)
                    putExtra("CODE", itemKode.text)
                    putExtra("CATEGORY", itemKategori.text)
                    putExtra("CONTENT", itemIsi.text)
                }

                context.run {

                    startActivity(intent)
                }
            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {

        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cardview_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemKode.text = kode[i]
        viewHolder.itemKategori.text = kategori[i]
        viewHolder.itemIsi.text = isi[i]


    }

    override fun getItemCount(): Int {
        return kode.size
    }
}




