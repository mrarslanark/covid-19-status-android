package com.example.covid_19status.response

import com.google.gson.annotations.SerializedName
import java.util.*

class CommonResponse {

    @SerializedName("Country")
    val country: String? = null

    @SerializedName("Province")
    val province: String? = null

    @SerializedName("Lat")
    val lat: Double? = null

    @SerializedName("Lon")
    val lng: Double? = null

    @SerializedName("Date")
    val date: Date? = null

    @SerializedName("Cases")
    val cases: Int? = null

    @SerializedName("Status")
    val status: String? = null
    override fun toString(): String {
        return "DayOneResponse(country=$country, " +
                "province=$province, " +
                "lat=$lat, lng=$lng, " +
                "date=$date, " +
                "cases=$cases, " +
                "status=$status)"
    }


}