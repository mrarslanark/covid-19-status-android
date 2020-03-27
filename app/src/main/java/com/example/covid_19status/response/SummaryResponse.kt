package com.example.covid_19status.response

import com.google.gson.annotations.SerializedName

class SummaryResponse {

    @SerializedName("Countries")
    val countries: List<Countries>? = null

}

class Countries {

    @SerializedName("Country")
    val country: String? = null

    @SerializedName("Slug")
    val countrySlug: String? = null

    @SerializedName("NewConfirmed")
    val newConfirmed: Int? = null

    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int? = null

    @SerializedName("NewDeaths")
    val newDeaths: Int? = null

    @SerializedName("TotalDeaths")
    val totalDeaths: Int? = null

    @SerializedName("NewRecovered")
    val newRecovered: Int? = null

    @SerializedName("TotalRecovered")
    val totalRecovered: Int? = null
    override fun toString(): String {
        return "Countries(country=$country, " +
                "countrySlug=$countrySlug, " +
                "newConfirmed=$newConfirmed, " +
                "totalConfirmed=$totalConfirmed, " +
                "newDeaths=$newDeaths, " +
                "totalDeaths=$totalDeaths, " +
                "newRecovered=$newRecovered, " +
                "totalRecovered=$totalRecovered)"
    }


}