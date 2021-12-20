package com.example.mydoctor

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydoctor.api.ApiInterface
import com.example.mydoctor.databinding.FragmentPrescriptionsBinding
import com.example.mydoctor.models.PrescriptionsResponse
import kotlinx.android.synthetic.main.cardview_layout_prescriptions.*
import kotlinx.android.synthetic.main.fragment_prescriptions.*

import kotlinx.android.synthetic.main.frame_layout.*
import kotlinx.android.synthetic.main.frame_layout.recycler_view
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_4 = "https://docappmy.heroku.com/mydoctor/"

class PrescriptionsFragment : Fragment() {

    lateinit var prescriptionsRecyclerAdapter: PrescriptionsRecyclerAdapter
    lateinit var  linearLayoutManager: LinearLayoutManager

    private lateinit var binding: FragmentPrescriptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_prescriptions, container, false)

        recycler_view_prescriptions.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(activity)
        recycler_view_prescriptions.layoutManager = linearLayoutManager
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val getDiagnosesList= mutableListOf<PrescriptionsResponse>()

//        recycler_view.apply {
//            // set a LinearLayoutManager to handle Android
//            // RecyclerView behavior
//            layoutManager = LinearLayoutManager(activity)
//            // set the custom adapter to the RecyclerView
//            adapter = PrescriptionsRecyclerAdapter(getDiagnosesList)
//        }

        val sharedPrefsToken2: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val tokenPrescriptions = sharedPrefsToken2.getString("sharedprefstoken"," ").toString()
        Log.d("tokenPrescriptions", "tokenPrescriptions is: $tokenPrescriptions")

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_4)
            .build()
            .create(ApiInterface::class.java)



        val retrofitDataPrescriptions = retrofitBuilder.getPrescriptions(tokenPrescriptions)

        retrofitDataPrescriptions.enqueue(object: Callback<List<PrescriptionsResponse>?> {
            override fun onResponse(call: Call<List<PrescriptionsResponse>?>, response: Response<List<PrescriptionsResponse>?>) {

                val responseBodyPrescriptions = response.body()!!

                prescriptionsRecyclerAdapter = context?.let { PrescriptionsRecyclerAdapter(it,responseBodyPrescriptions) }!!
                prescriptionsRecyclerAdapter.notifyDataSetChanged()
                recycler_view_prescriptions.adapter = prescriptionsRecyclerAdapter

            }
            override fun onFailure(call: Call<List<PrescriptionsResponse>?>, t: Throwable) {
                Log.d("Prescriptions Fragment","onFailure:" + t.message)

            }
        })

    }

}