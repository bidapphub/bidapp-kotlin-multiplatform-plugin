package io.bidapp.demo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bidapp.compose.BIDBannerState
import io.bidapp.compose.BIDEventType
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
    var buttonState by mutableStateOf(StateButton())

    init {
        ads.startSdk()
        ads.createInterstitial()
        ads.createRewarded()
    }

    fun createAdsEvents() {
        viewModelScope.launch {
            val adsEvent = object : AdsEvents {
                override fun interstitialLoad() {
                    buttonState = buttonState.copy(isEnableInterstitial = true)
                }

                override fun rewardedLoad() {
                    buttonState = buttonState.copy(isEnableRewarded = true)
                }
            }
            ads.setAdEvents(adsEvent)
        }
    }

    fun onClickEvent(onClickEvent: OnClickEvent) {
        when (onClickEvent) {
            OnClickEvent.SHOW_INTERSTITIAL -> ads.showInterstitial()
            OnClickEvent.SHOW_REWARDED -> ads.showRewarded()
            OnClickEvent.SHOW_REFRESH_BANNER -> bannerState =
                BIDBannerState.ShowingWithOutAutoRefresh()
            OnClickEvent.START_STOP_AUTO_REFRESH_BANNER -> {
                if (bannerState is BIDBannerState.ShowingWithOutAutoRefresh || (bannerState as? BIDBannerState.ShowingWithAutoRefresh)?.isStopAutoRefresh() == true){
                    val startAutoRefresh = BIDBannerState.ShowingWithAutoRefresh()
                    buttonState = buttonState.copy(isAutoRefresh = true)
                    startAutoRefresh.setInterval(30.0)
                    bannerState = startAutoRefresh
                } else {
                    val stopAutoRefresh = BIDBannerState.ShowingWithAutoRefresh()
                    buttonState = buttonState.copy(isAutoRefresh = false)
                    stopAutoRefresh.stop(true)
                    bannerState = stopAutoRefresh
                }
            }
            OnClickEvent.DESTROY_BANNER -> {
                bannerState = BIDBannerState.Destroyed
                buttonState = buttonState.copy(isShowingBanner = false)
            }
        }
    }

    fun onBannerEvent(bidEventType: BIDEventType){
        when (bidEventType) {
            is BIDEventType.BidBannerEventTypeOnAllNetworksFailedToDisplay -> log("BidBannerEventTypeOnAllNetworksFailedToDisplay")
            is BIDEventType.BidBannerEventTypeOnClick -> log("BidBannerEventTypeOnClick - ${bidEventType.adInfo}")
            is BIDEventType.BidBannerEventTypeOnDisplay -> {
                buttonState = buttonState.copy(isShowingBanner = true)
                log("BidBannerEventTypeOnDisplay - ${bidEventType.adInfo}")
            }
            is BIDEventType.BidBannerEventTypeOnFailToDisplay -> log("BidBannerEventTypeOnFailToDisplay - ${bidEventType.adInfo}")
            is BIDEventType.BidBannerEventTypeOnLoad -> log("BidBannerEventTypeOnLoad - ${bidEventType.adInfo}")
        }
    }

    fun destroy() {
        if (getPlatformName() == "Android") {
            ads.destroy()
        }
    }

}