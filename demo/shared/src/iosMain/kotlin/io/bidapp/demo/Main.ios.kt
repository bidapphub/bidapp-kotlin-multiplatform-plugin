package io.bidapp.demo



import androidx.compose.ui.window.ComposeUIViewController
import platform.Foundation.NSLog

fun MainViewController()  = ComposeUIViewController { App() }

actual fun log(message: String) {
    NSLog("bidappMPDemo $message")
}

