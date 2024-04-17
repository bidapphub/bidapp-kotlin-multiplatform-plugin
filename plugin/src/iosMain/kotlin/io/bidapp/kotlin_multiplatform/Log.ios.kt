package io.bidapp.kotlin_multiplatform

import platform.Foundation.NSLog

actual fun log(message: String) {
    if (BIDLog.logEnabled){
        NSLog("bidappMP - $message")
    }
}