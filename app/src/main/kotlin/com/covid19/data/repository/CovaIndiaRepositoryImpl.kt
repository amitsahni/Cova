package com.covid19.data.repository

import com.covid19.data.global.CovaIndiaApi
import com.covid19.data.model.response.india.NotificationItem
import com.covid19.data.model.wrapped.Response
import com.covid19.data.model.wrapped.toResponseBody


/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

class CovaIndiaRepositoryImpl(private val repo: CovaIndiaApi) : CovaIndiaRepository {
    override suspend fun getNotification(): Response<List<NotificationItem>> {
        return repo.getNotification().toResponseBody()
    }
}