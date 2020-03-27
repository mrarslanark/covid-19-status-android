package com.example.covid_19status.response

import com.google.gson.annotations.SerializedName

class CountryResponse {

    @SerializedName("Country")
    val country: String? = null

    @SerializedName("Slug")
    val slug: String? = null

    @SerializedName("Provinces")
    val provinces: List<String>? = null
    override fun toString(): String {
        return "CountryResponse(country=$country, " +
                "slug=$slug, " +
                "provinces=$provinces)"
    }


}