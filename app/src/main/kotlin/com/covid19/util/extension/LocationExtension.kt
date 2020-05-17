package com.fptechscience.tila.common.extension

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
//import com.google.android.gms.common.api.ApiException
//import com.google.android.gms.common.api.ResolvableApiException
//import com.google.android.gms.location.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*


const val LOCATIONCODE = 1010

/*** LocationRequest for getting current Location ***//*
private val locationRequest by lazy {
    LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval((10 * 1000).toLong())
            .setFastestInterval((1 * 1000).toLong())
}

*//*** LocationBuilder for getting current Location ***//*
private val locationBuilder by lazy {
    val settingsBuilder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
    settingsBuilder.setAlwaysShow(true)
    settingsBuilder
}


fun AppCompatActivity.locationAddress(fusedLocationClient: FusedLocationProviderClient, block: (Address) -> Unit) {
    val task = LocationServices.getSettingsClient(this)
            .checkLocationSettings(locationBuilder.build())
    task.addOnSuccessListener {
        applicationContext.locationAddress(fusedLocationClient, block)
    }

    task.addOnCanceledListener {
        Timber.d("addOnCanceledListener")
    }

    task.addOnFailureListener { e ->
        val it = e as ApiException
        when (it.statusCode) {
            LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                try {
                    val resolvable = it as ResolvableApiException
                    resolvable.startResolutionForResult(
                            this,
                            LOCATIONCODE)
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
            LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                Timber.e("Location settings are inadequate, and cannot be fixed here. Fix in Settings.")
            }
        }
    }

}


fun Context.locationAddress(fusedLocationClient: FusedLocationProviderClient, block: (Address) -> Unit) {
    fusedLocationClient.lastLocation.addOnSuccessListener {
        if (it != null) {
            getInfo(it) {
                block(it)
            }
        } else {
            fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult?) {
                    p0?.lastLocation?.let {
                        getInfo(it) {
                            block(it)
                        }
                    }
                    fusedLocationClient.removeLocationUpdates(this)
                }
            }, Looper.getMainLooper())
        }
    }
}

fun Context.getInfo(location: Location, f: (Address) -> Unit) {
    val geocoder = Geocoder(this, Locale.getDefault())
    val handlerException = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
    CoroutineScope(Dispatchers.IO).launch(handlerException) {
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        addresses.forEach {
            it?.let(f)
        }
    }
}*/
