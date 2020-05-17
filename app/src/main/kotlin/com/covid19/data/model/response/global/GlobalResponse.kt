/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.data.model.response.global

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*** TODO api/v1/cases/general-stats ***/
@Serializable
data class GeneralStatResponse(
    @SerialName("data")
    private val `data`: GeneralStat? = null,

    @SerialName("status")
    val status: String? = null // success
) {
    val generalStat: GeneralStat
        get() {
            return status?.let {
                data
            } ?: GeneralStat()
        }
}

@Serializable
data class GeneralStat(
    @SerialName("active_cases_critical_percentage")
    val activeCasesCriticalPercentage: String? = null, // 2.0

    @SerialName("active_cases_mild_percentage")
    val activeCasesMildPercentage: String? = null, // 98.0

    @SerialName("cases_with_outcome")
    val casesWithOutcome: String? = null, // 2,059,090

    @SerialName("closed_cases_death_percentage")
    val closedCasesDeathPercentage: String? = null, // 15.0

    @SerialName("closed_cases_recovered_percentage")
    val closedCasesRecoveredPercentage: String? = null, // 85.0

    @SerialName("critical_condition_active_cases")
    val criticalConditionActiveCases: String? = null, // 45,012

    @SerialName("currently_infected")
    val currentlyInfected: String? = null, // 2,560,396

    @SerialName("death_cases")
    val deathCases: String? = null, // 308,108

    @SerialName("death_closed_cases")
    val deathClosedCases: String? = null, // 308,108

    @SerialName("general_death_rate")
    val generalDeathRate: String? = null, // 7.000000000000001

    @SerialName("last_update")
    val lastUpdate: String? = null, // May, 15 2020, 23:29, UTC

    @SerialName("mild_condition_active_cases")
    val mildConditionActiveCases: String? = null, // 2,515,384

    @SerialName("recovered_closed_cases")
    val recoveredClosedCases: String? = null, // 1,750,982

    @SerialName("recovery_cases")
    val recoveryCases: String? = null, // 1,750,982

    @SerialName("total_cases")
    val totalCases: String? = null // 4,619,486
)


/*** TODO /api/v1/cases/countries-search?limit=50 ***/
@Serializable
data class CountrySearchResponse(
    @SerialName("data")
    private val `data`: CountrySearch? = null,

    @SerialName("status")
    val status: String? = null // success
) {
    val countrySearch: CountrySearch
        get() {
            return status?.let {
                data
            } ?: CountrySearch()
        }
}

@Serializable
data class CountrySearch(
    @SerialName("last_update")
    val lastUpdate: String? = null, // May, 16 2020, 05:19, UTC

    @SerialName("paginationMeta")
    val paginationMeta: PaginationMeta? = null,

    @SerialName("rows")
    val rows: List<Row>? = null
)

@Serializable
data class PaginationMeta(
    @SerialName("currentPage")
    val currentPage: Int? = null, // 1

    @SerialName("currentPageSize")
    val currentPageSize: Int? = null, // 50

    @SerialName("totalPages")
    val totalPages: Int? = null, // 5

    @SerialName("totalRecords")
    val totalRecords: Int? = null // 220
)

@Serializable
data class Row(
    @SerialName("active_cases")
    val activeCases: String? = null, // 2,518,010

    @SerialName("cases_per_mill_pop")
    val casesPerMillPop: String? = null, // 581.0

    @SerialName("country")
    val country: String? = null, // World

    @SerialName("country_abbreviation")
    val countryAbbreviation: String? = null,

    @SerialName("flag")
    val flag: String? = null, // https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/International_Flag_of_Planet_Earth.svg/800px-International_Flag_of_Planet_Earth.svg.png

    @SerialName("new_cases")
    val newCases: String? = null, // 3,077

    @SerialName("new_deaths")
    val newDeaths: String? = null, // 269

    @SerialName("serious_critical")
    val seriousCritical: String? = null, // 45,560

    @SerialName("total_cases")
    val totalCases: String? = null, // 4,525,103

    @SerialName("total_deaths")
    val totalDeaths: String? = null, // 303,351

    @SerialName("total_recovered")
    val totalRecovered: String? = null // 1,703,742
)