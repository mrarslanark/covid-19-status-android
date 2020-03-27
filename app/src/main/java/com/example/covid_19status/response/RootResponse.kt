package com.example.covid_19status.response

import com.google.gson.annotations.SerializedName

class RootResponse {

    @SerializedName("Name")
    val name: String? = null

    @SerializedName("Description")
    val description: String? = null

    @SerializedName("Path")
    val path: String? = null

    @SerializedName("Params")
    val params: List<String>? = null

    override fun toString(): String {
        return "RootResponse(name=$name, description=$description, path=$path, params=$params)"
    }

}
