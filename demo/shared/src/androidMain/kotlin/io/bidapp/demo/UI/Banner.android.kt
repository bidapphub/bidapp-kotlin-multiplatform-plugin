package io.bidapp.demo.UI

import android.view.View
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import io.bidapp.kmp.BIDBanner
import io.bidapp.kmp.log

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
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                val view = FrameLayout(context)
                addBanner(view, banner)
                onsuccess(view as View)
                view
            }
        )
    }
}

actual fun addBanner(view: Any, banner: BIDBanner?) {
    (view as FrameLayout).addView(banner?.bannerView)
}

actual fun removeBanner(view: Any) {
    (view as FrameLayout).removeAllViews()
}