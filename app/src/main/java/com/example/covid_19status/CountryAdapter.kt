package com.example.covid_19status

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19status.Constants.Companion.LOG_TAG
import com.example.covid_19status.databinding.CountryItemViewBinding
import com.example.covid_19status.response.CountryResponse

class CountryAdapter internal constructor(
    private val countriesList: List<CountryResponse>,
    private val countryClickListener: CountryClickListener
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder internal constructor(
        private val binding: CountryItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(countries: CountryResponse) {
            binding.country = countries
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = CountryItemViewBinding.inflate(layoutInflater, parent, false)
        return CountryViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countries = countriesList[position]
        if (countries.country == "" || countries.slug?.startsWith("-")!!) {
            holder.itemView.visibility = View.GONE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
        holder.bind(countries)
        holder.itemView.setOnClickListener {
            if (countries.slug == null) {
                Log.d(LOG_TAG, "slug == null")
                Toast.makeText(
                    holder.itemView.context,
                    holder.itemView.context.getString(R.string.ERROR_700),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            countryClickListener.countryClickListener(countries.slug)
        }
    }

    interface CountryClickListener {
        fun countryClickListener(slug: String)
    }

}