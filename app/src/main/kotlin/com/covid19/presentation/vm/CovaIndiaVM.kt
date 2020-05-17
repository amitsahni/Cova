package com.covid19.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.covid19.domain.usecase.india.NotificationUseCase


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class CovaIndiaVM(
    private val notificationUseCase: NotificationUseCase
) : ViewModel() {


    private val _notificationLiveData = MutableLiveData<Unit>()
    val notificationLiveData = Transformations.switchMap(_notificationLiveData) {
        notificationUseCase.execute(viewModelScope)
    }


    fun onAction(action: CovaIndiaAction) {
        when (action) {

            is CovaIndiaAction.Notification -> {
                _notificationLiveData.value = Unit
            }

        }
    }
}

sealed class CovaIndiaAction {

    object Notification : CovaIndiaAction()

}