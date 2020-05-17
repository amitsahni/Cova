package com.covid19.domain.manager

import com.covid19.data.model.response.india.NotificationItem
import com.covid19.data.model.wrapped.Result
import com.covid19.data.model.wrapped.toResult
import com.covid19.data.repository.CovaIndiaRepository


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class CovaIndiaManagerImpl(private val repo: CovaIndiaRepository) : CovaIndiaManager {
    override suspend fun getNotification(): Result<List<NotificationItem>> {
        return repo.getNotification().toResult()
    }


}