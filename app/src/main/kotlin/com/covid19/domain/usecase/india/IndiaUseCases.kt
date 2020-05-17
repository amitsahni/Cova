/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.domain.usecase.india

import com.covid19.data.model.response.india.NotificationItem
import com.covid19.data.model.wrapped.Result
import com.covid19.domain.manager.CovaIndiaManager
import com.covid19.domain.usecase.UseCase

class NotificationUseCase(private val covaIndiaManager: CovaIndiaManager) :
    UseCase<Unit, List<NotificationItem>> {

    override suspend fun onExecute(parameter: Unit?): Result<List<NotificationItem>> {
        return covaIndiaManager.getNotification()
    }

}