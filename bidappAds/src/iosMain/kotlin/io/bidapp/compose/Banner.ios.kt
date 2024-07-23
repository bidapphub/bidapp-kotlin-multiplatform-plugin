package io.bidapp.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import io.bidapp.core.PlatformView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun NativeView(adView: PlatformView) {
    UIKitView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            adView.bannerView ?: UIView()
        })
}