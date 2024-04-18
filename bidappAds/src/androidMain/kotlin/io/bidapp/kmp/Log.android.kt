package io.bidapp.kmp

import android.util.Log
import io.bidapp.kmp.BIDLog

actual fun log(message: String) {
    if (BIDLog.logEnabled){
        Log.d("bidappMP - ", message)
    }
}