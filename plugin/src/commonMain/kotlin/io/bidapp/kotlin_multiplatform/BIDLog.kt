package io.bidapp.kotlin_multiplatform

internal object BIDLog {
    var logEnabled = false
}

expect fun log(message:String)