package com.covid19.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.covid19.data.model.request.global.CountrySearchRequest
import com.covid19.domain.usecase.global.CountrySearchUseCase
import com.covid19.domain.usecase.global.GeneralStatUseCase


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class CovaGlobalVM(
    private val generalStatUseCase: GeneralStatUseCase,
    private val countrySearchUseCase: CountrySearchUseCase
) : ViewModel() {

    private val _generalStatLiveData = MutableLiveData<Unit>()
    val generalStatLiveData = Transformations.switchMap(_generalStatLiveData) {
        generalStatUseCase.execute(viewModelScope)
    }

    private val _countrySearchLiveData = MutableLiveData<CountrySearchRequest>()
    val countrySearchLiveData = Transformations.switchMap(_countrySearchLiveData) {
        countrySearchUseCase.execute(viewModelScope,it)
    }

    fun onAction(action: CovaGlobalAction) {
        when (action) {

            is CovaGlobalAction.GeneralStat -> {
                _generalStatLiveData.value = Unit
            }

            is CovaGlobalAction.CountrySearch -> {
                _countrySearchLiveData.value = action.request
            }
        }
    }

}

sealed class CovaGlobalAction {

    object GeneralStat : CovaGlobalAction()

    data class CountrySearch(val request: CountrySearchRequest? = null) : CovaGlobalAction()
}