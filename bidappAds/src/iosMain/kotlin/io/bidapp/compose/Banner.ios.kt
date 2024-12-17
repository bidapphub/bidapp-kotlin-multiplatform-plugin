package io.bidapp.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import io.bidapp.core.PlatformView
import platform.UIKit.UIView

@Composable
internal actual fun NativeView(adView: PlatformView, modifier: Modifier)
{
    UIKitView(
        factory = {
            adView.bannerView ?: UIView()
        }, modifier = modifier,
        properties = UIKitInteropProperties(
            isInteractive = true,
            isNativeAccessibilityEnabled = true
        )
    )
}