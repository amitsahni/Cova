/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.data.model.request.global

import kotlinx.serialization.Serializable

@Serializable
data class CountrySearchRequest(
    val search: String? = null,
    val page: String? = null
)