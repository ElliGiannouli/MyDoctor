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
import com.example.mydoctor.databinding.FragmentCalendarBinding
import com.example.mydoctor.databinding.FragmentDiagnosisBinding
import com.example.mydoctor.models.DiagnosisResponse
import kotlinx.android.synthetic.main.cardview_layout.*
import kotlinx.android.synthetic.main.frame_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_3 = "https://docappmy.heroku.com/mydoctor/"

class DiagnosisFragment : Fragment() {
    //   var toolbar: Toolbar? = null
    //   private var layoutManager: RecyclerView.LayoutManager? = null
    //   private var adapterDiagnosis: RecyclerView.Adapter<DiagnosisRecyclerAdapter.ViewHolder>? = null
    //   var recyclerView: RecyclerView? = null

    private lateinit var binding: FragmentDiagnosisBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val sharedPrefsTokenCalendar: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val tokenCalendar = sharedPrefsTokenCalendar.getString("sharedprefstoken"," ").toString()
        Log.d("tokenCalendar", "tokenCalendar is: $tokenCalendar")

        val getDiagnosesList= mutableListOf<DiagnosisResponse>()

        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = DiagnosisRecyclerAdapter(getDiagnosesList)
        }

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(APP_URL)
            .build()
            .create(ApiInterface::class.java)

        val sharedPrefsToken2: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val tokenDiagnosis = sharedPrefsToken2.getString("sharedprefstoken"," ").toString()
        Log.d("tokenDiagnosis", "tokenDiagnosis is: $tokenDiagnosis")

        val retrofitDataDiagnosis = retrofitBuilder.getDiagnosis(tokenDiagnosis)

        retrofitDataDiagnosis.enqueue(object: Callback<DiagnosisResponse> {
            override fun onResponse(call: Call<DiagnosisResponse>, response: Response<DiagnosisResponse>) {

                val responseBodyDiagnosis = response.body()!!

            }
            override fun onFailure(call: Call<DiagnosisResponse>, t: Throwable) {
                TODO("Not yet implemented")

            }
    })

}

}