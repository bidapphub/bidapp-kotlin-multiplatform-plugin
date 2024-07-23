package io.bidapp.core

internal object BIDLog {
    var logEnabled = false
}

public expect fun log(message:String)