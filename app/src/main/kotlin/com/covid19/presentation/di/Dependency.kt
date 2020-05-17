/**
 * Created by Amit Singh on 04/05/20.
 * Tila
 * asingh@tila.com
 */

package com.covid19.presentation.di

import com.covid19.data.global.CovaGlobalApi
import com.covid19.data.global.CovaIndiaApi
import com.covid19.data.repository.CovaGlobalRepository
import com.covid19.data.repository.CovaGlobalRespositoryImpl
import com.covid19.data.repository.CovaIndiaRepository
import com.covid19.data.repository.CovaIndiaRepositoryImpl
import com.covid19.domain.manager.CovaGlobalManager
import com.covid19.domain.manager.CovaGlobalManagerImpl
import com.covid19.domain.manager.CovaIndiaManager
import com.covid19.domain.manager.CovaIndiaManagerImpl
import com.covid19.domain.usecase.global.CountrySearchUseCase
import com.covid19.domain.usecase.global.GeneralStatUseCase
import com.covid19.domain.usecase.india.NotificationUseCase
import com.covid19.presentation.vm.CovaGlobalVM
import com.covid19.presentation.vm.CovaIndiaVM
import com.covid19.presentation.vm.DashBoardVM
import com.covid19.util.http.RetrofitManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun dependency() = listOf(vm, repository, manager, service, useCases)

val vm = module {
    viewModel { DashBoardVM() }
    // TODO Coupled -> GeneralStatUseCase,CountrySearchUseCase
    viewModel { CovaGlobalVM(get(), get()) }
    // TODO Coupled ->
    viewModel { CovaIndiaVM(get()) }
}

val useCases = module {
    // TODO Coupled -> CovaGlobalManager
    factory { GeneralStatUseCase(get()) }
    factory { CountrySearchUseCase(get()) }

    // TODO Coupled -> CovaIndiaManager
    factory { NotificationUseCase(get()) }
}

val manager = module {
    // TODO Coupled -> CovaGlobalRepository
    single { CovaGlobalManagerImpl(get()) as CovaGlobalManager }

    // TODO Coupled -> CovaIndiaRepository
    single { CovaIndiaManagerImpl(get()) as CovaIndiaManager }
}

val repository = module {
    // TODO Coupled -> CovaGlobalApi
    single { CovaGlobalRespositoryImpl(get()) as CovaGlobalRepository }

    // TODO Coupled -> CovaIndiaApi
    single { CovaIndiaRepositoryImpl(get()) as CovaIndiaRepository }
}

val service = module {
    single { CovaGlobalApi.createInstance(RetrofitManager.retrofit("https://corona-virus-stats.herokuapp.com")) }
    single { CovaIndiaApi.createInstance(RetrofitManager.retrofit("https://api.covid19india.org")) }
}

