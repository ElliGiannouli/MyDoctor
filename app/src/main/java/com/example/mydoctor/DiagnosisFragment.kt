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
import com.example.mydoctor.databinding.FragmentDiagnosisBinding
import com.example.mydoctor.models.DiagnosisResponse
import kotlinx.android.synthetic.main.cardview_layout_diagnosis.*
import kotlinx.android.synthetic.main.fragment_diagnosis.*
import kotlinx.android.synthetic.main.frame_layout.*
import kotlinx.android.synthetic.main.frame_layout.recycler_view
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_3 = "https://docappmy.herokuapp.com/mydoctor/"

class DiagnosisFragment : Fragment() {

    lateinit var diagnosisRecyclerAdapter: DiagnosisRecyclerAdapter
    lateinit var  linearLayoutManager: LinearLayoutManager

    private lateinit var binding: FragmentDiagnosisBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_diagnosis, container, false)

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val sharedPrefsToken2: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val tokenDiagnosis = sharedPrefsToken2.getString("sharedprefstoken"," ").toString()
        Log.d("tokenDiagnosis", "tokenDiagnosis is: $tokenDiagnosis")

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_3)
            .build()
            .create(ApiInterface::class.java)

        val retrofitDataDiagnosis = retrofitBuilder.getDiagnosis(tokenDiagnosis)

        retrofitDataDiagnosis.enqueue(object: Callback<List<DiagnosisResponse>?> {
            override fun onResponse(call: Call<List<DiagnosisResponse>?>, response: Response<List<DiagnosisResponse>?>) {

                val responseBodyDiagnosis = response.body()!!
                Log.d("responsebodydiagnosis","Response Body diagnosis is: $responseBodyDiagnosis")

                recycler_view_diagnosis.setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(activity)
                recycler_view_diagnosis.layoutManager = linearLayoutManager

                diagnosisRecyclerAdapter = context?.let { DiagnosisRecyclerAdapter(it,responseBodyDiagnosis) }!!
               // diagnosisRecyclerAdapter.notifyDataSetChanged()
                recycler_view_diagnosis.adapter = diagnosisRecyclerAdapter

            }
            override fun onFailure(call: Call<List<DiagnosisResponse>?>, t: Throwable) {
                Log.d("DiagnosisFragment","onFailure:" + t.message)

            }
    })

}

}