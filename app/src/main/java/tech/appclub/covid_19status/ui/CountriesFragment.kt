package tech.appclub.covid_19status.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.appclub.covid_19status.MainActivity
import tech.appclub.covid_19status.R
import tech.appclub.covid_19status.adapters.CountryAdapter
import tech.appclub.covid_19status.common.Constants.Companion.LOG_TAG
import tech.appclub.covid_19status.common.Constants.Companion.NINJA_COVID19_URL
import tech.appclub.covid_19status.common.Constants.Companion.isConnected
import tech.appclub.covid_19status.databinding.FragmentCountriesBinding
import tech.appclub.covid_19status.interfaces.APIService
import tech.appclub.covid_19status.response.CountryResponse

class CountriesFragment : Fragment(), CountryAdapter.CountryClickListener {

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

        if (isConnected(requireContext())) {
            loadData()
        } else {
            showAlertDialogBox()
        }

        binding.swipeRefreshLayout?.setOnRefreshListener {
            if (isConnected(requireContext())) {
                loadData()
            } else {
                showAlertDialogBox()
            }
            binding.swipeRefreshLayout?.isRefreshing = false
        }

    }

    private fun showAlertDialogBox() {
        val dialogBox = (requireActivity() as MainActivity).alertDialogBox()
        val button = dialogBox.getButton(AlertDialog.BUTTON_POSITIVE)
        button.setOnClickListener {
            if (isConnected(requireContext())) {
                loadData()
                dialogBox.dismiss()
            } else {
                Toast.makeText(
                    requireContext(), getString(R.string.no_network_toast),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun loadData() {
        val responseListener = object : ResponseListener {
            override fun onSuccess(
                call: Call<List<CountryResponse>>,
                response: Response<List<CountryResponse>>
            ) {
                val data = response.body()
                if (data != null) {
                    val adapter = CountryAdapter(
                        data.sortedBy { it.country },
                        this@CountriesFragment
                    )
                    binding.countryRv.adapter = adapter
                    binding.progressCircular.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<CountryResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }
        }
        getData(responseListener)
    }

    private interface ResponseListener {
        fun onSuccess(call: Call<List<CountryResponse>>, response: Response<List<CountryResponse>>)
        fun onFailure(call: Call<List<CountryResponse>>, t: Throwable)
    }

    private fun getData(responseListener: ResponseListener) {

        val retrofit = Retrofit.Builder()
            .baseUrl(NINJA_COVID19_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)
        val call = service.getCountries(NINJA_COVID19_URL)
        call.enqueue(object : Callback<List<CountryResponse>> {
            override fun onResponse(
                call: Call<List<CountryResponse>>,
                response: Response<List<CountryResponse>>
            ) {
                responseListener.onSuccess(call, response)
            }

            override fun onFailure(call: Call<List<CountryResponse>>, t: Throwable) {
                responseListener.onFailure(call, t)
            }

        })

    }

    override fun countryClickListener(country: CountryResponse) {
        val action = CountriesFragmentDirections.countryToCountryDetailDest(country)
        findNavController().navigate(action)
    }

}
