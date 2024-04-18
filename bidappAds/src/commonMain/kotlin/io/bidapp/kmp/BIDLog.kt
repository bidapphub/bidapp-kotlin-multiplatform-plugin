package io.bidapp.kmp

internal object BIDLog {
    var logEnabled = false
}

expect fun log(message:String)