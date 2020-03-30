package tech.appclub.covid_19status.interfaces


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tech.appclub.covid_19status.response.CountryResponse
import tech.appclub.covid_19status.response.TotalResponse

interface APIService {

    // region https://corona.lmao.ninja/all
    @GET("/all")
    fun getTotalData(@Query("APPID") APP_ID: String): Call<TotalResponse>

    @GET("/countries?sort=country")
    fun getCountries(@Query("APPID") APP_ID: String): Call<List<CountryResponse>>

    @GET("/countries/{country}")
    fun getCountry(
        @Path("country") country: String,
        @Query("APPID") APP_ID: String
    ): Call<CountryResponse>

    // endregion

}