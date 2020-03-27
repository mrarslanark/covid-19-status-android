package com.example.covid_19status

import com.example.covid_19status.response.CountryResponse
import com.example.covid_19status.response.CommonResponse
import com.example.covid_19status.response.RootResponse
import com.example.covid_19status.response.SummaryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("/")
    fun getRootData(@Query("APPID") APP_ID: String): Call<List<RootResponse>>

    @GET("/summary")
    fun getSummaryData(@Query("APPID") APP_ID: String): Call<SummaryResponse>

    @GET("/countries")
    fun getCountriesData(@Query("APPID") APP_ID: String): Call<List<CountryResponse>>

    @GET("/dayone/country/{slug}/status/{status}")
    fun getDayOneData(
        @Path("slug") slug: String,
        @Path("status") status: String,
        @Query("APPID") APP_ID: String
    ): Call<List<CommonResponse>>

    @GET("/dayone/country/{slug}/status/{status}/live")
    fun getDayOneLiveData(
        @Path("slug") slug: String,
        @Path("status") status: String,
        @Query("APPID") APP_ID: String
    ): Call<List<CommonResponse>>

    @GET("/total/dayone/country/{slug}/status/{status}")
    fun getDayOneTotalData(
        @Path("slug") slug: String,
        @Path("status") status: String,
        @Query("APPID") APP_ID: String
    ): Call<List<CommonResponse>>

    @GET("/country/{slug}/status/{status}")
    fun getDataByCountry(
        @Path("slug") slug: String,
        @Path("status") status: String,
        @Query("APPID") APP_ID: String
    ): Call<List<CommonResponse>>

    @GET("/country/{slug}/status/{status}/live")
    fun getDataByCountryLive(
        @Path("slug") slug: String,
        @Path("status") status: String,
        @Query("APPID") APP_ID: String
    ): Call<List<CommonResponse>>

    @GET("/total/country/{slug}/status/{status}")
    fun getDataByCountryTotal(
        @Path("slug") slug: String,
        @Path("status") status: String,
        @Query("APPID") APP_ID: String
    ): Call<List<CommonResponse>>

    @GET("/live/country/{slug}/status/{status}")
    fun getLiveByCountryAndStatus(
        @Path("slug") slug: String,
        @Path("status") status: String,
        @Query("APPID") APP_ID: String
    ): Call<List<CommonResponse>>

}