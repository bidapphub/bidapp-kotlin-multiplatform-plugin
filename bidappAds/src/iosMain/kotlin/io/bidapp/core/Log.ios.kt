package io.bidapp.core


import platform.Foundation.NSLog

public actual fun log(message: String) {
    if (BIDLog.logEnabled){
        NSLog("bidappMP - $message")
    }
}