package io.bidapp.demo


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.bidapp.compose.BIDBannerState
import io.bidapp.demo.Ads.AppAds
import io.bidapp.demo.Ads.data.AdsEvents
import io.bidapp.demo.Ads.data.BidappAdsImpl
import io.bidapp.core.getPlatformName


class AdsAppData {
    private val ads: AppAds by lazy {
        BidappAdsImpl()
    }
    var bannerState = mutableStateOf<BIDBannerState>(BIDBannerState.NotDisplayed)
    var displayBanner : MutableState<Boolean> = mutableStateOf(false)
    var isInterstitialLoad : MutableState<Boolean> = mutableStateOf(false)
    var isRewardedLoad : MutableState<Boolean> = mutableStateOf(false)

    init {
        ads.startSdk()
        ads.createInterstitial()
        ads.createRewarded()
    }

    fun createAdsEvents() {
        val adsEvent = object : AdsEvents {
            override fun displayBanner() {
                displayBanner.value = true
            }

            override fun interstitialLoad() {
                isInterstitialLoad.value = true
            }

            override fun rewardedLoad() {
                isRewardedLoad.value = true
            }

        }
        ads.setAdEvents(adsEvent)
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


expect fun log(message: String)

