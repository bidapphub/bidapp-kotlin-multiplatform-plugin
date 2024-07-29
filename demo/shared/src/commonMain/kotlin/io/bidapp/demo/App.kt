package io.bidapp.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.bidapp.compose.Banner
import io.bidapp.core.BIDAdFormat
import io.bidapp.demo.UI.ButtonAds
import io.bidapp.demo.UI.Logo


@Composable
fun App() {
    val viewModelAdsData = viewModel{ AdsDataViewModel() }

    LaunchedEffect(Unit) {
        viewModelAdsData.createAdsEvents()
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModelAdsData.destroy()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(top = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        ButtonAds(viewModelAdsData.buttonState, onClick = { event ->
            viewModelAdsData.onClickEvent(event)
        })
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Banner(viewModelAdsData.bannerState, BIDAdFormat.banner_320x50, onEvent = { event ->
            viewModelAdsData.onBannerEvent(event)
        })
    }
}


enum class OnClickEvent{
    SHOW_INTERSTITIAL,
    SHOW_REWARDED,
    SHOW_REFRESH_BANNER,
    START_STOP_AUTO_REFRESH_BANNER,
    DESTROY_BANNER
}

data class StateButton(
    var isEnableInterstitial : Boolean = false,
    var isEnableRewarded : Boolean = false,
    var isShowingBanner : Boolean = false,
    var isAutoRefresh : Boolean = false
)


expect fun log(message: String)



