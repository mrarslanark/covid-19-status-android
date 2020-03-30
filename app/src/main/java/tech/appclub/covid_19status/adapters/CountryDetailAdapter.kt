package tech.appclub.covid_19status.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.covid_19status.databinding.CountryItemViewBinding
import tech.appclub.covid_19status.response.CountryResponse

class CountryDetailAdapter internal constructor(
    private val countriesDetailList: List<CountryResponse>
) : RecyclerView.Adapter<CountryDetailAdapter.CountryDetailViewHolder>() {

    inner class CountryDetailViewHolder internal constructor(
        private val binding: CountryItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(countries: CountryResponse) {
            binding.countries = countries
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = CountryItemViewBinding.inflate(layoutInflater, parent, false)
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