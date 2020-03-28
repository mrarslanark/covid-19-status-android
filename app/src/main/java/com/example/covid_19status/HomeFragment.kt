package com.example.covid_19status

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.covid_19status.Constants.Companion.COIVD19_APP_ID
import com.example.covid_19status.Constants.Companion.LOG_TAG
import com.example.covid_19status.databinding.FragmentHomeBinding
import com.example.covid_19status.response.SummaryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

        binding.refresh.setOnRefreshListener {
            binding.updatingLabel.visibility = View.VISIBLE
            binding.progressBar.visibility = View.VISIBLE
            getData()
            binding.refresh.isRefreshing = false
        }
    }

    private fun getData() {
        commit(object : HomeRetrofitResponseListener {
            override fun onSuccess(
                call: Call<SummaryResponse>,
                response: Response<SummaryResponse>
            ) {
                binding.updatingLabel.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<SummaryResponse>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

        })
    }

    private fun commit(listener: HomeRetrofitResponseListener) {

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)
        val call = service.getSummaryData(COIVD19_APP_ID)
        call.enqueue(object : Callback<SummaryResponse> {
            override fun onFailure(call: Call<SummaryResponse>, t: Throwable) {
                listener.onFailure(call, t)
            }

            override fun onResponse(
                call: Call<SummaryResponse>,
                response: Response<SummaryResponse>
            ) {
                val data = response.body()
                var recovered = 0
                var deaths = 0
                var cases = 0
                data?.countries?.forEach { country ->
                    recovered += country.totalRecovered ?: 0
                    deaths += country.totalDeaths ?: 0
                    cases += country.totalConfirmed ?: 0
                }
                binding.totalCases.text = cases.toString()
                binding.totalDeaths.text = deaths.toString()
                binding.totalRecovered.text = recovered.toString()
                listener.onSuccess(call, response)
            }
        })
    }

    interface HomeRetrofitResponseListener {

        fun onSuccess(call: Call<SummaryResponse>, response: Response<SummaryResponse>)
        fun onFailure(call: Call<SummaryResponse>, t: Throwable)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                binding.updatingLabel.visibility = View.VISIBLE
                binding.progressBar.visibility = View.VISIBLE
                getData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
