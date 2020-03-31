package tech.appclub.covid_19status.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.appclub.covid_19status.R
import tech.appclub.covid_19status.common.Constants.Companion.LOG_TAG
import tech.appclub.covid_19status.common.Constants.Companion.NINJA_COVID19_APP_ID
import tech.appclub.covid_19status.common.Constants.Companion.NINJA_COVID19_URL
import tech.appclub.covid_19status.databinding.FragmentHomeBinding
import tech.appclub.covid_19status.interfaces.APIService
import tech.appclub.covid_19status.response.TotalResponse

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        this.binding.appClubLink.setOnClickListener {
            val uri = Uri.parse("https://appclub.tech/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }

    private fun loadData() {
        binding.progressBar.visibility = View.VISIBLE
        val responseListener = object : HomeResponseListener {
            override fun onSuccess(call: Call<TotalResponse>, response: Response<TotalResponse>) {
                val data = response.body()
                if (data != null) {
                    binding.total = data
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }

            override fun onFailure(call: Call<TotalResponse>, t: Throwable) {
                Log.d(LOG_TAG, t.localizedMessage, t)
            }
        }
        getData(responseListener)
    }

    private fun getData(responseListener: HomeResponseListener) {
        val retrofit = Retrofit.Builder()
            .baseUrl(NINJA_COVID19_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)
        val call = service.getTotalData(NINJA_COVID19_APP_ID)
        call.enqueue(object : Callback<TotalResponse> {
            override fun onFailure(call: Call<TotalResponse>, t: Throwable) {
                responseListener.onFailure(call, t)
            }

            override fun onResponse(call: Call<TotalResponse>, response: Response<TotalResponse>) {
                responseListener.onSuccess(call, response)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                loadData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private interface HomeResponseListener {
        fun onSuccess(call: Call<TotalResponse>, response: Response<TotalResponse>)
        fun onFailure(call: Call<TotalResponse>, t: Throwable)
    }

}
