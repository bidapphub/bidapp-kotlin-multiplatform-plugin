package io.bidapp.compose

import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import io.bidapp.core.PlatformView

@Composable
internal actual fun NativeView(adView: PlatformView, modifier: Modifier)
{
    AndroidView(
        modifier = modifier,
        factory = { context->
            adView.bannerView ?: FrameLayout(context)
        })
}