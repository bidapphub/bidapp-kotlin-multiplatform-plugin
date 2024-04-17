package io.bidapp.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.bidapp.demo.UI.ButtonAds
import io.bidapp.demo.UI.Logo
import io.bidapp.demo.Data.AdsEvents
import io.bidapp.demo.Data.BIDAppAdsData
import io.bidapp.demo.UI.Banner
import io.bidapp.kotlin_multiplatform.getPlatformName

@Composable
fun App(bidappAdsData: BIDAppAdsData, activityOrUIViewController: Any?) {
    var adsEvents: AdsEvents? by remember { mutableStateOf(null) }
    val isAutoRefreshOn = remember { mutableStateOf(false) }
    val displayBanner = remember { mutableStateOf(false) }
    val isInterstitialLoad = remember { mutableStateOf(false) }
    val isRewardedLoad = remember { mutableStateOf(false) }
    val view: MutableState<Any?> = remember { mutableStateOf(Any()) }

    LaunchedEffect(Unit) {
        if (adsEvents == null) {
            adsEvents = object : AdsEvents {
                override fun displayBanner() {
                    displayBanner.value = true
                }

                override fun interstitialLoad() {
                    isInterstitialLoad.value = true
                }

                override fun rewardedLoad() {
                    isRewardedLoad.value = true
                }

                override fun loadBanner(networkID: Int?) {
                    if (getPlatformName() == "Android" && networkID == 7) requestLayout(view.value)
                }
            }
            bidappAdsData.adsEvents = adsEvents
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            if (getPlatformName() == "Android") {
                bidappAdsData.destroy()
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        ButtonAds(
            bidappAdsData,
            activityOrUIViewController,
            view,
            isAutoRefreshOn,
            displayBanner,
            isInterstitialLoad,
            isRewardedLoad
        )
    }
    Banner(bidappAdsData, view)
}


expect fun log(message: String)


//Fix admob banner impression callback on compose for Android
expect fun requestLayout(view:Any?)



