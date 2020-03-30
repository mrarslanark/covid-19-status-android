package tech.appclub.covid_19status.ui

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import tech.appclub.covid_19status.R
import tech.appclub.covid_19status.common.Constants.Companion.LOG_TAG
import tech.appclub.covid_19status.databinding.FragmentCountryDetailBinding
import tech.appclub.covid_19status.response.CountryResponse
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class CountryDetailFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentCountryDetailBinding
    private lateinit var country: CountryResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val safeArgs: CountryDetailFragmentArgs by navArgs()
        country = safeArgs.response

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setTitle(country.country)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_nav)
        }

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_country_detail,
            container, false
        )
        return binding.root
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        this.binding.country = country

    }

    override fun onMapReady(map: GoogleMap?) {
        if (map != null) {
            Log.d(LOG_TAG, "map is not null")
            val latLng = LatLng(country.countryInfo?.lat!!, country.countryInfo?.lng!!)
            with(map) {
                moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 4f))
                addMarker(MarkerOptions().position(latLng))
                setMinZoomPreference(4f)
            }

        } else {
            Log.d(LOG_TAG, "map is null")
            return
        }
    }
}
