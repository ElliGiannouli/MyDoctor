package com.example.mydoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoctor.PrescriptionsRecyclerAdapter
import com.example.mydoctor.R
import com.example.mydoctor.R.*
import kotlinx.android.synthetic.main.fragment_dia_recyclerview.*
import kotlinx.android.synthetic.main.fragment_prescriptions.*
import kotlinx.android.synthetic.main.fragment_dia_recyclerview.*

/**
 * A simple [Fragment] subclass.
 */
class DiagnosisFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<PrescriptionsRecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.fragment_diagnoses, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = DiagnosisRecyclerAdapter()
        }
    }
}