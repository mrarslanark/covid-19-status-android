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
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.text.DecimalFormat

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formatter = DecimalFormat()

        try {
            val obj = JSONObject(loadJSONFromAssets()!!)
            val jsonArray = obj.getJSONArray("Data")

            var totalConfirmed = 0
            var totalDeaths = 0
            var totalRecovered = 0
            var totalActive = 0

            for (i in 0 until jsonArray.length()) {
                val inside = jsonArray.getJSONObject(i)
                val confirmed = inside.get("Confirmed") as Int
                val deaths = inside.get("Deaths") as Int
                val recovered = inside.get("Recovered") as Int
                val active = inside.get("Active") as Int

                totalConfirmed += confirmed
                totalDeaths += deaths
                totalRecovered += recovered
                totalActive += active
            }
            this.binding.totalCases.text = formatter.format(totalConfirmed)
            this.binding.totalDeaths.text = formatter.format(totalDeaths)
            this.binding.totalRecovered.text = formatter.format(totalRecovered)
            this.binding.totalActive.text = formatter.format(totalActive)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    private fun loadJSONFromAssets(): String? {
        val json: String?
        try {
            val file = requireActivity().assets.open("coid_19_latest.json")
            val size = file.available()
            val buffer = ByteArray(size)
            file.read(buffer)
            file.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
