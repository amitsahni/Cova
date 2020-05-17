package com.covid19.data.global

import com.covid19.data.model.response.india.NotificationItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET


/**
 * Created by Amit Singh on 16/05/20.
 * Tila
 * asingh@tila.com
 */

interface CovaIndiaApi {

    companion object {
        fun createInstance(retrofit: Retrofit): CovaIndiaApi {
            return retrofit.create(CovaIndiaApi::class.java)
        }
    }

    @GET("/updatelog/log.json")
    suspend fun getNotification(): Response<List<NotificationItem>>
}