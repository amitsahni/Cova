package com.covid19.data.repository

import com.covid19.data.global.CovaGlobalApi
import com.covid19.data.model.response.global.CountrySearchResponse
import com.covid19.data.model.response.global.GeneralStatResponse
import com.covid19.data.model.wrapped.Response
import com.covid19.data.model.wrapped.toResponseBody


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class CovaGlobalRespositoryImpl(private val api : CovaGlobalApi) : CovaGlobalRepository {

    override suspend fun getGeneralStat(): Response<GeneralStatResponse> {
        return api.getGeneralStat().toResponseBody()
    }

    override suspend fun countrySearch(map: Map<String, String>): Response<CountrySearchResponse> {
        return api.countrySearch(map).toResponseBody()
    }
}