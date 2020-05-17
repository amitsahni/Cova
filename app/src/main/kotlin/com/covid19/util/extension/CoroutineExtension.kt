/**
 * Created by Amit Singh on 2020-02-05.
 * Tila
 * asingh@tila.com
 */

package com.fptechscience.tila.common.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
val ioDispatcher = Dispatchers.IO
val ioScope = CoroutineScope(ioDispatcher)

val mainDispatcher = Dispatchers.IO
val mainScope = CoroutineScope(mainDispatcher)

