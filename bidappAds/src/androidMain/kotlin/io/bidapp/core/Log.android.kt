package io.bidapp.core

import android.util.Log

public actual fun log(message: String) {
    if (BIDLog.logEnabled){
        Log.d("bidappMP - ", message)
    }
}