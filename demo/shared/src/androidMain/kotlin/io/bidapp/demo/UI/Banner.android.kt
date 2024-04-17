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
import io.bidapp.kotlin_multiplatform.BIDBanner

@Composable
actual fun ShowBanner(banner : BIDBanner?, onsuccess:(view : Any)->Unit)  {
    val width = remember {  mutableIntStateOf(if (banner?.getBannerSize()?.isBanner_320x50() == true) 320 else 300) }
    val height = remember { mutableIntStateOf(if (banner?.getBannerSize()?.isBanner_320x50() == true) 50 else 250) }
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
                banner?.bindBanner(view)
                onsuccess(view as View)
                view
            }
        )
    }
}