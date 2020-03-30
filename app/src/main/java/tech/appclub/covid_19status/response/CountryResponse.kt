package tech.appclub.covid_19status.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CountryResponse : Serializable {

    @SerializedName("country")
    val country: String? = null

    @SerializedName("countryInfo")
    val countryInfo: CountryInfo? = null

    @SerializedName("cases")
    val cases: Long? = null

    @SerializedName("todayCases")
    val todayCases: Long? = null

    @SerializedName("deaths")
    val deaths: Long? = null

    @SerializedName("todayDeaths")
    val todayDeaths: Long? = null

    @SerializedName("recovered")
    val recovered: Long? = null

    @SerializedName("active")
    val active: Long? = null

    @SerializedName("critical")
    val critical: Long? = null

    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Double? = null

    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Double? = null

    @SerializedName("updated")
    val updated: Long? = null
}

class CountryInfo {

    @SerializedName("_id")
    val id: Long? = null

    @SerializedName("iso2")
    val iso2: String? = null

    @SerializedName("iso3")
    val iso3: String? = null

    @SerializedName("lat")
    val lat: Double? = null

    @SerializedName("long")
    val lng: Double? = null

    @SerializedName("flag")
    val flag: String? = null

}