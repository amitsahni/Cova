package com.covid19.presentation.ui.base

import androidx.multidex.MultiDexApplication
import com.covid19.BuildConfig
import com.covid19.presentation.di.dependency
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class AppApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(LineNumberDebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(dependency())
        }
    }
}

class LineNumberDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}