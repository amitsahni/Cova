package com.covid19.data.repository

import com.covid19.data.model.response.india.NotificationItem
import com.covid19.data.model.wrapped.Response


/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

interface CovaIndiaRepository {

    suspend fun getNotification(): Response<List<NotificationItem>>
}