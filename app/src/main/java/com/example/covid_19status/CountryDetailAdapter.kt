package com.example.covid_19status

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19status.databinding.CountryDetailItemViewBinding
import com.example.covid_19status.response.CommonResponse

class CountryDetailAdapter internal constructor(
    private val countriesDetailList: List<CommonResponse>
) : RecyclerView.Adapter<CountryDetailAdapter.CountryDetailViewHolder>() {

    inner class CountryDetailViewHolder internal constructor(
        private val binding: CountryDetailItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(countries: CommonResponse) {
            binding.country = countries
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = CountryDetailItemViewBinding.inflate(layoutInflater, parent, false)
        return CountryDetailViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return countriesDetailList.size
    }

    override fun onBindViewHolder(holder: CountryDetailViewHolder, position: Int) {
        val country = countriesDetailList[position]
        holder.bind(country)
    }

}