package io.bidapp.demo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bidapp.compose.BIDBannerState
import io.bidapp.core.getPlatformName
import io.bidapp.demo.Ads.AppAds
import io.bidapp.demo.Ads.data.AdsEvents
import io.bidapp.demo.Ads.data.BidappAdsImpl
import kotlinx.coroutines.launch

class AdsDataViewModel : ViewModel() {
    private val ads: AppAds by lazy {
        BidappAdsImpl()
    }
    var bannerState by mutableStateOf<BIDBannerState>(BIDBannerState.NotDisplayed)
    var displayBanner by mutableStateOf(false)
    var isInterstitialLoad by mutableStateOf(false)
        private set
    var isRewardedLoad by mutableStateOf(false)
        private set

    init {
        ads.startSdk()
        ads.createInterstitial()
        ads.createRewarded()
    }

    fun createAdsEvents() {
        viewModelScope.launch {
            val adsEvent = object : AdsEvents {
                override fun displayBanner() {
                    displayBanner = true
                }

                override fun interstitialLoad() {
                    isInterstitialLoad = true
                }

                override fun rewardedLoad() {
                    isRewardedLoad = true
                }

            }
            ads.setAdEvents(adsEvent)
        }
    }

    fun showInterstitial(){
        ads.showInterstitial()
    }

    fun showRewarded(){
        ads.showRewarded()
    }

    fun destroy() {
        if (getPlatformName() == "Android") {
            ads.destroy()
        }
    }
}