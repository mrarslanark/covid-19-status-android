package tech.appclub.covid_19status.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.covid_19status.databinding.CountryItemViewBinding
import tech.appclub.covid_19status.response.CountryResponse

class CountryAdapter internal constructor(
    private val countriesList: List<CountryResponse>,
    private val countryClickListener: CountryClickListener
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder internal constructor(
        private val binding: CountryItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(countries: CountryResponse) {
            binding.countries = countries
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
        val country = countriesList[position]
        holder.bind(country)
        holder.itemView.setOnClickListener {
            countryClickListener.countryClickListener(country)
        }
    }

    interface CountryClickListener {
        fun countryClickListener(country: CountryResponse)
    }

}