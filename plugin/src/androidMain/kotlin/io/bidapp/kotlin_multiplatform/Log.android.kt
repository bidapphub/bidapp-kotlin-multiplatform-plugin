package io.bidapp.kotlin_multiplatform

import android.util.Log

actual fun log(message: String) {
    if (BIDLog.logEnabled){
        Log.d("bidappMP - ", message)
    }
}