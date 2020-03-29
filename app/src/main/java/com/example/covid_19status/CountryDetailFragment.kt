package com.example.covid_19status

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.covid_19status.databinding.FragmentCountryDetailBinding
import com.example.covid_19status.response.CommonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CountryDetailFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_country_detail,
            container, false
        )
        return binding.root
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs: CountryDetailFragmentArgs by navArgs()
        val slug = safeArgs.slug
        val defaultStatus = "confirmed"

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setTitle(slug.capitalize(Locale.getDefault()))
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.COVID19_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)
        val call = service.getDataByCountryLive(slug, defaultStatus, Constants.COIVD19_APP_ID)
        call.enqueue(object : Callback<List<CommonResponse>> {
            override fun onFailure(call: Call<List<CommonResponse>>, t: Throwable) {
                Log.d(Constants.LOG_TAG, t.localizedMessage, t)
            }

            override fun onResponse(
                call: Call<List<CommonResponse>>,
                response: Response<List<CommonResponse>>
            ) {
                val data = response.body()
                binding.countryDetailRv.adapter = CountryDetailAdapter(data!!)
            }
        })

        //
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.country_detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_scroll_bottom -> {
                binding.countryDetailRv.post {
                    if (binding.countryDetailRv.adapter != null) {
                        binding
                            .countryDetailRv
                            .smoothScrollToPosition(
                                binding
                                    .countryDetailRv.adapter?.itemCount!! - 1
                            )
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
