package com.covid19.data.global

import com.covid19.data.model.response.global.CountrySearchResponse
import com.covid19.data.model.response.global.GeneralStatResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.QueryMap


/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

interface CovaGlobalApi {

    companion object {
        fun createInstance(retrofit: Retrofit): CovaGlobalApi {
            return retrofit.create(CovaGlobalApi::class.java)
        }
    }

    @GET("/api/v1/cases/general-stats")
    suspend fun getGeneralStat(): Response<GeneralStatResponse>

    @GET("/api/v1/cases/countries-search?limit=20&how=asc")
    suspend fun countrySearch(@QueryMap map: Map<String, String>): Response<CountrySearchResponse>
}