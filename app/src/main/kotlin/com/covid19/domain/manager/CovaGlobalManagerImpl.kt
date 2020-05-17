package com.covid19.domain.manager

import com.covid19.data.model.response.global.CountrySearchResponse
import com.covid19.data.model.response.global.GeneralStatResponse
import com.covid19.data.model.wrapped.Response
import com.covid19.data.model.wrapped.Result
import com.covid19.data.model.wrapped.toResult
import com.covid19.data.repository.CovaGlobalRepository


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class CovaGlobalManagerImpl(private val repo: CovaGlobalRepository) : CovaGlobalManager {

    override suspend fun getGeneralStat(): Result<GeneralStatResponse> {
        return repo.getGeneralStat().toResult()
    }

    override suspend fun countrySearch(map: Map<String, String>): Result<CountrySearchResponse> {
        return repo.countrySearch(map).toResult()
    }
}