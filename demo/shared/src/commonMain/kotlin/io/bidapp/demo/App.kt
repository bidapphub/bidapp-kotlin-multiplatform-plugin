package io.bidapp.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.bidapp.compose.BIDEventType
import io.bidapp.compose.Banner
import io.bidapp.core.BIDAdFormat
import io.bidapp.demo.UI.ButtonAds
import io.bidapp.demo.UI.Logo


@Composable
fun App() {
    val adsAppData = remember { AdsAppData() }
    LaunchedEffect(Unit) {
        adsAppData.createAdsEvents()
    }
    DisposableEffect(Unit) {
        onDispose {
            adsAppData.destroy()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(top = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        ButtonAds(adsAppData)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Banner(adsAppData.bannerState.value, BIDAdFormat.banner_320x50, onEvent = { event ->
                when (event) {
                    is BIDEventType.BidBannerEventTypeOnAllNetworksFailedToDisplay -> log("BidBannerEventTypeOnAllNetworksFailedToDisplay")
                    is BIDEventType.BidBannerEventTypeOnClick -> log("BidBannerEventTypeOnClick - ${event.adInfo}")
                    is BIDEventType.BidBannerEventTypeOnDisplay -> {
                        adsAppData.displayBanner.value = true
                        log("BidBannerEventTypeOnDisplay - ${event.adInfo}")
                    }

                    is BIDEventType.BidBannerEventTypeOnFailToDisplay -> log("BidBannerEventTypeOnFailToDisplay - ${event.adInfo}")
                    is BIDEventType.BidBannerEventTypeOnLoad -> log("BidBannerEventTypeOnLoad - ${event.adInfo}")
                }
            })
    }
}






