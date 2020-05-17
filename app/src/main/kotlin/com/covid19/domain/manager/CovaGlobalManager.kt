package com.covid19.domain.manager

import com.covid19.data.model.response.global.CountrySearchResponse
import com.covid19.data.model.response.global.GeneralStatResponse
import com.covid19.data.model.wrapped.Response
import com.covid19.data.model.wrapped.Result


/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

interface CovaGlobalManager {

    suspend fun getGeneralStat()  :Result<GeneralStatResponse>

    suspend fun countrySearch(map: Map<String,String>) : Result<CountrySearchResponse>
}