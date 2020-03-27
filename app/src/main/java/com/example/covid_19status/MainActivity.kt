package com.example.covid_19status

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.covid_19status.Constants.Companion.BaseURL
import com.example.covid_19status.Constants.Companion.COIVD19_APP_ID
import com.example.covid_19status.Constants.Companion.LOG_TAG
import com.example.covid_19status.databinding.ActivityMainBinding
import com.example.covid_19status.response.CommonResponse
import com.example.covid_19status.response.CountryResponse
import com.example.covid_19status.response.RootResponse
import com.example.covid_19status.response.SummaryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        val host =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // region Setup Action Bar
        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home_dest, R.id.countries_dest, R.id.safety_dest)
        )

        binding.bottomNavView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // val retrofit = Retrofit.Builder()
        //    .baseUrl(BaseURL)
        //    .addConverterFactory(GsonConverterFactory.create())
        //    .build()

        // val service = retrofit.create(APIService::class.java)

        // region Data Functions

        // getRootData(service)
        // getSummaryData(service)
        // getCountriesData(service)
        // getDayOneData("pakistan", "recovered", service)
        // getDayOneLiveData("pakistan", "confirmed", service)
        // getDayOneTotalData("pakistan", "deaths", service)
        // getDataByCountry("pakistan", "recovered", service)
        // getDataByCountryLive("pakistan", "deaths", service)
        // getDataByCountryTotal("pakistan", "confirmed", service)
        // getLiveByCountryAndStatus("pakistan", "confirmed", service)

        // endregion

    }

    // region Origin -> https://api.covid19api.com/live/country/{slug}/status/{status}
    private fun getLiveByCountryAndStatus(slug: String, status: String, service: APIService) {
        val call = service.getLiveByCountryAndStatus(slug, status, COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CommonResponse>> {
            override fun onFailure(call: Call<List<CommonResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<List<CommonResponse>>,
                response: Response<List<CommonResponse>>
            ) {
                val data = response.body()
                data?.forEach { countryData ->
                    Log.d(LOG_TAG, countryData.toString())
                }
            }

        })
    }

    // endregion

    // region Origin -> https://api.covid19api.com/total/country/{slug}/status/{status}
    private fun getDataByCountryTotal(slug: String, status: String, service: APIService) {
        val call = service.getDataByCountryTotal(slug, status, COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CommonResponse>> {
            override fun onFailure(call: Call<List<CommonResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<List<CommonResponse>>,
                response: Response<List<CommonResponse>>
            ) {
                val data = response.body()
                data?.forEach { countryData ->
                    Log.d(LOG_TAG, countryData.toString())
                }
            }

        })
    }

    // endregion

    // region Origin -> https://api.covid19api.com/country/{slug}/status/{status}/live
    private fun getDataByCountryLive(slug: String, status: String, service: APIService) {
        val call = service.getDataByCountryLive(slug, status, COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CommonResponse>> {
            override fun onFailure(call: Call<List<CommonResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<List<CommonResponse>>,
                response: Response<List<CommonResponse>>
            ) {
                val data = response.body()
                data?.forEach { countryData ->
                    Log.d(LOG_TAG, countryData.toString())
                }
            }

        })
    }

    // endregion

    // region Origin -> https://api.covid19api.com/country/{slug}/status/{status}
    private fun getDataByCountry(slug: String, status: String, service: APIService) {
        val call = service.getDataByCountry(slug, status, COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CommonResponse>> {
            override fun onFailure(call: Call<List<CommonResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<List<CommonResponse>>,
                response: Response<List<CommonResponse>>
            ) {
                val data = response.body()
                data?.forEach { countryData ->
                    Log.d(LOG_TAG, countryData.toString())
                }
            }

        })
    }

    // endregion

    // region Origin -> https://api.covid19api.com/dayone/country/{slug}/status/{confirmed}/live
    private fun getDayOneTotalData(slug: String, status: String, service: APIService) {
        val call = service.getDayOneTotalData(slug, status, COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CommonResponse>> {
            override fun onFailure(call: Call<List<CommonResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<List<CommonResponse>>,
                response: Response<List<CommonResponse>>
            ) {
                val data = response.body()
                data?.forEach { countryData ->
                    Log.d(LOG_TAG, countryData.toString())
                }
            }

        })
    }

    // endregion

    // region Origin -> https://api.covid19api.com/dayone/country/{slug}/status/{confirmed}/live
    private fun getDayOneLiveData(slug: String, status: String, service: APIService) {
        val call = service.getDayOneLiveData(slug, status, COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CommonResponse>> {
            override fun onFailure(call: Call<List<CommonResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<List<CommonResponse>>,
                response: Response<List<CommonResponse>>
            ) {
                val data = response.body()
                data?.forEach { countryData ->
                    Log.d(LOG_TAG, countryData.toString())
                }
            }

        })
    }

    // endregion

    // region  Origin -> https://api.covid19api.com/dayone/country/{slug}/{status}/
    private fun getDayOneData(slug: String, status: String, service: APIService) {
        val call = service.getDayOneData(slug, status, COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CommonResponse>> {
            override fun onFailure(call: Call<List<CommonResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<List<CommonResponse>>,
                response: Response<List<CommonResponse>>
            ) {
                val data = response.body()
                data?.forEach { countryData ->
                    Log.d(LOG_TAG, countryData.toString())
                }
            }

        })
    }

    // endregion

    // region Origin -> https://api.covid19api.com/countries
    private fun getCountriesData(service: APIService) {
        val call = service.getCountriesData(COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CountryResponse>> {
            override fun onFailure(call: Call<List<CountryResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<List<CountryResponse>>,
                response: Response<List<CountryResponse>>
            ) {
                val data = response.body()
                Log.d(LOG_TAG, data.toString())
            }

        })
    }

    // endregion

    // region Origin -> https://api.covid19api.com/summary
    private fun getSummaryData(service: APIService) {
        val call = service.getSummaryData(COIVD19_APP_ID)
        call.enqueue(object : Callback<SummaryResponse> {

            override fun onFailure(call: Call<SummaryResponse>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<SummaryResponse>,
                response: Response<SummaryResponse>
            ) {
                val data = response.body()
                var counter: Int = 0
                data?.countries?.forEach { country ->
                    // Log.d(LOG_TAG, country.toString())
                    counter += country.totalRecovered!!
                }
                Log.d(LOG_TAG, "$counter")
            }
        })
    }

    // endregion

    // region Origin -> https://api.covid19api.com/
    private fun getRootData(service: APIService) {
        val call = service.getRootData(COIVD19_APP_ID)
        call.enqueue(object : Callback<List<RootResponse>> {

            override fun onResponse(
                call: Call<List<RootResponse>>,
                response: Response<List<RootResponse>>
            ) {
                val data = response.body()
                Log.d(LOG_TAG, data.toString())
            }

            override fun onFailure(call: Call<List<RootResponse>>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }

        })
    }

    // endregionplayer55

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }
}