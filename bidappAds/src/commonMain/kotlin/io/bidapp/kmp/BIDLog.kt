package io.bidapp.kmp

internal object BIDLog {
    var logEnabled = false
}

public expect fun log(message:String)