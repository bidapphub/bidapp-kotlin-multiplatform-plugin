package io.bidapp.demo.UI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import io.bidapp.kmp.BIDBanner
import io.bidapp.kmp.log
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIView


@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
actual fun CreateBannerPlace(banner : BIDBanner?, onsuccess:(view : Any)->Unit)  {
    val width = remember {  mutableIntStateOf(
        if (banner?.bidBannerSize?.isBanner_320x50() == true) 320
        else if (banner?.bidBannerSize?.isBanner_300x250() == true) 300
        else if (banner?.bidBannerSize?.isBanner_728x90() == true) 728
        else {
            log("Incorrect banner format")
            0
        }) }
    val height = remember { mutableIntStateOf(
        if (banner?.bidBannerSize?.isBanner_320x50() == true) 50
        else if (banner?.bidBannerSize?.isBanner_300x250() == true) 250
        else if (banner?.bidBannerSize?.isBanner_728x90() == true) 90
        else {
            log("Incorrect banner format")
            0
        }) }
    Box(
        modifier = Modifier
            .width(width.intValue.dp)
            .height(height.intValue.dp)
            .fillMaxWidth()
    ) {
        UIKitView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                val view = banner?.bannerView ?: UIView
                addBanner(view, banner)
                onsuccess(view as UIView)
                view
            }
        )
    }
}

actual fun addBanner(view: Any, banner: BIDBanner?) {
    banner?.bannerView?.let { (view as UIView).addSubview(it) }
}

actual fun removeBanner(view: Any) {
    (view as UIView).removeFromSuperview()
}