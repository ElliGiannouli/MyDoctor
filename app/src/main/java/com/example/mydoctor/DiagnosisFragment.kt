package com.example.mydoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydoctor.models.DiagnosisResponse
import kotlinx.android.synthetic.main.frame_layout.*

const val DIA_URL = "https://docappmy.heroku.com/mydoctor/diagnosis"

class DiagnosisFragment : Fragment() {
    //   var toolbar: Toolbar? = null
    //   private var layoutManager: RecyclerView.LayoutManager? = null
    //   private var adapterDiagnosis: RecyclerView.Adapter<DiagnosisRecyclerAdapter.ViewHolder>? = null
    //   var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val getDiagnosesList= mutableListOf<DiagnosisResponse>()

        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = DiagnosisRecyclerAdapter(getDiagnosesList)
        }
    }




}