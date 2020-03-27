package com.example.covid_19status

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.covid_19status.Constants.Companion.BaseURL
import com.example.covid_19status.databinding.FragmentCountriesBinding
import com.example.covid_19status.response.CountryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass.
 */
class CountriesFragment : Fragment(),
    CountryAdapter.CountryClickListener {

    private lateinit var binding: FragmentCountriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_countries, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commit(object : CountriesRetrofitResponseListener {
            override fun onSuccess(
                call: Call<List<CountryResponse>>,
                response: Response<List<CountryResponse>>
            ) {
                val data = response.body()
                val adapter = CountryAdapter(data!!, this@CountriesFragment)
                binding.countryRv.adapter = adapter
                binding.progressCircular.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<CountryResponse>>, t: Throwable) {
                Log.d(Constants.LOG_TAG, t.localizedMessage, t)
            }

        })
    }

    private fun commit(countriesRetrofitResponseListener: CountriesRetrofitResponseListener) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)
        val call = service.getCountriesData(Constants.COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CountryResponse>> {
            override fun onFailure(call: Call<List<CountryResponse>>, t: Throwable) {
                countriesRetrofitResponseListener.onFailure(call, t)
            }

            override fun onResponse(
                call: Call<List<CountryResponse>>,
                response: Response<List<CountryResponse>>
            ) {
                countriesRetrofitResponseListener.onSuccess(call, response)
            }
        })
    }

    interface CountriesRetrofitResponseListener {

        fun onSuccess(call: Call<List<CountryResponse>>, response: Response<List<CountryResponse>>)
        fun onFailure(call: Call<List<CountryResponse>>, t: Throwable)
    }

    override fun countryClickListener(slug: String) {
        Toast.makeText(requireContext(), slug, Toast.LENGTH_LONG).show()
    }

}
