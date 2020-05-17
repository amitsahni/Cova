/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.domain.usecase.global

import com.covid19.data.model.request.global.CountrySearchRequest
import com.covid19.data.model.response.global.CountrySearchResponse
import com.covid19.data.model.response.global.GeneralStatResponse
import com.covid19.data.model.wrapped.Result
import com.covid19.domain.manager.CovaGlobalManager
import com.covid19.domain.usecase.UseCase
import com.fptechscience.tila.common.extension.fromJson
import com.fptechscience.tila.common.extension.toJson

class GeneralStatUseCase(private val covaGlobalManager: CovaGlobalManager) :
    UseCase<Unit, GeneralStatResponse> {

    override suspend fun onExecute(parameter: Unit?): Result<GeneralStatResponse> {
        return covaGlobalManager.getGeneralStat()
    }
}


class CountrySearchUseCase(private val covaGlobalManager: CovaGlobalManager) :
    UseCase<CountrySearchRequest, CountrySearchResponse> {

    override suspend fun onExecute(parameter: CountrySearchRequest?): Result<CountrySearchResponse> {
        val map: Map<String, String> = parameter?.toJson()?.fromJson() ?: emptyMap()
        return covaGlobalManager.countrySearch(map)
    }
}