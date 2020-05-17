/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.data.model.response.india

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NotificationItem(
    @SerialName("timestamp")
    val timestamp: Int? = null, // 1589618523

    @SerialName("update")
    val update: String? = null // 438 new cases, 408 recoveries and 6 deaths in Delhi
)