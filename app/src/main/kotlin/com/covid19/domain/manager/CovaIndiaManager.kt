package com.covid19.domain.manager

import com.covid19.data.model.response.india.NotificationItem
import com.covid19.data.model.wrapped.Result


/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

interface CovaIndiaManager {

    suspend fun getNotification(): Result<List<NotificationItem>>

}