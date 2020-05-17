@file:JvmName("LogUtils")

package com.fptechscience.tila.common.extension

import com.fptechscience.tila.common.extension.Log.IS_DEBUG
import timber.log.Timber

object Log {
    var IS_DEBUG = true
}

fun String.printInfo() {
    if (IS_DEBUG) {
        Timber.i(this)
    }

}

fun String.printError() {
    if (IS_DEBUG) {
        Timber.e(this)
    }
}

fun String.printWarn() {
    if (IS_DEBUG) {
        Timber.w(this)
    }
}

fun Any.printInfo() {
    if (IS_DEBUG) {
        Timber.i(this.toJson())
    }
}

fun Any.printError() {
    if (IS_DEBUG) {
        Timber.e(this.toJson())
    }
}

fun Any.printWarn() {
    if (IS_DEBUG) {
        Timber.w(this.toJson())
    }
}

