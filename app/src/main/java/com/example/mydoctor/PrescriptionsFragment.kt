package com.example.mydoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydoctor.models.PrescriptionsResponse
import kotlinx.android.synthetic.main.frame_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PrescriptionsFragment : Fragment() {


  //  private var layoutManager: RecyclerView.LayoutManager? =null
  //  private var adapterDiagnosis: RecyclerView.Adapter<PrescriptionsRecyclerAdapter.ViewHolder>? =
  //      null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }




    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val getPrescriptionsList = mutableListOf<PrescriptionsResponse>()


        recycler_view.apply {
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = PrescriptionsRecyclerAdapter(getPrescriptionsList)





        }
    }
}





