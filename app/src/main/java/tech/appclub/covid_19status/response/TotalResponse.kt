package tech.appclub.covid_19status.response

import com.google.gson.annotations.SerializedName

class TotalResponse {

    @SerializedName("cases")
    val cases: Long? = null

    @SerializedName("deaths")
    val deaths: Long? = null

    @SerializedName("recovered")
    val recovered: Long? = null

    @SerializedName("updated")
    val updated: Long? = null

    @SerializedName("active")
    val active: Long? = null

    override fun toString(): String {
        return "TotalResponse(cases=$cases, deaths=$deaths, recovered=$recovered, updated=$updated, active=$active)"
    }


}