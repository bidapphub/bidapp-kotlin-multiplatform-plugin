package io.bidapp.demo



import androidx.compose.ui.window.ComposeUIViewController
import io.bidapp.demo.Data.BIDAppAdsData
import platform.Foundation.NSLog



fun MainViewController(bidappAdsData: BIDAppAdsData)  = ComposeUIViewController { App(bidappAdsData, null) }

actual fun log(message: String) {
    NSLog("bidappMPDemo $message")
}


actual fun requestLayout(view: Any?) {
}