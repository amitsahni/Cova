package com.covid19.data.repository

import com.covid19.data.model.response.global.CountrySearchResponse
import com.covid19.data.model.response.global.GeneralStatResponse
import com.covid19.data.model.wrapped.Response


/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

interface CovaGlobalRepository {

    suspend fun getGeneralStat()  :Response<GeneralStatResponse>

    suspend fun countrySearch(map: Map<String,String>) : Response<CountrySearchResponse>
}