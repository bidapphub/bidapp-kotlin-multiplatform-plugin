package io.bidapp.demo.Ads.data

import io.bidapp.demo.Ads.AppAds
import io.bidapp.demo.isEnableLogging
import io.bidapp.demo.isEnableTestMode
import io.bidapp.demo.log
import io.bidapp.demo.pubId
import io.bidapp.core.BIDAdInfo
import io.bidapp.core.BIDConfiguration
import io.bidapp.core.BIDFullLoad
import io.bidapp.core.BIDFullShow
import io.bidapp.core.BIDInterstitial
import io.bidapp.core.BIDRewarded
import io.bidapp.core.BidappAds

class BidappAdsImpl : AppAds {
    private var interstitial: BIDInterstitial? = null
    private var rewarded: BIDRewarded? = null
    var adsEvents : AdsEvents? = null
    private var fullInterstitialLoad : BIDFullLoad? = object : BIDFullLoad {
        override fun load(info: BIDAdInfo) {
            adsEvents?.interstitialLoad()
            log("Load - adtag: ${info.adTag}")
        }

        override fun failLoad(info: BIDAdInfo, error: String) {
            log("Fail to load - adtag: ${info.adTag} Error:$error")
        }

    }
    private var fullRewardedLoad : BIDFullLoad? = object : BIDFullLoad {
        override fun load(info: BIDAdInfo) {
            adsEvents?.rewardedLoad()
            log("Load - adtag: ${info.adTag}")
        }

        override fun failLoad(info: BIDAdInfo, error: String) {
            log("Fail to load - adtag: ${info.adTag} Error:$error")
        }

    }
    var fullShow : BIDFullShow? = object : BIDFullShow {
        override fun display(info: BIDAdInfo) {
            log("Display - adtag: ${info.adTag}")
        }

        override fun failToDisplay(info: BIDAdInfo, error: String) {
            log("Fail to display - adtag: ${info.adTag} Error:$error")
        }

        override fun click(info: BIDAdInfo) {
            log("Click - adtag: ${info.adTag}")
        }

        override fun hide(info: BIDAdInfo) {
            log("Hide - adtag: ${info.adTag}")
        }

        override fun allNetworksFailedToDisplay(error: String) {
            log("All networks fail to display - error: $error")
        }

        override fun reward() {
            log("Reward")
        }

    }

    override fun startSdk() {
        val bidConfig = BIDConfiguration().apply {
            if (isEnableLogging) enableLoggingAds()
            if (isEnableTestMode) enableTestModeAds()
        }
        BidappAds.start(pubId, bidConfig)
    }

    override fun setAdEvents(adsEvents: AdsEvents) {
        this.adsEvents = adsEvents
    }

    override fun createInterstitial() {
        interstitial = BIDInterstitial()
        interstitial?.setLoadDelegate(fullInterstitialLoad)
    }

    override fun createRewarded() {
        rewarded = BIDRewarded()
        rewarded?.setLoadDelegate(fullRewardedLoad)
    }


    override fun showInterstitial() {
        interstitial?.showInterstitial(fullShow)
    }

    override fun showRewarded() {
        rewarded?.showRewarded(fullShow)
    }

     override fun destroy() {
        interstitial?.destroy()
        rewarded?.destroy()
        interstitial = null
        rewarded = null
        fullInterstitialLoad = null
        fullRewardedLoad = null
        fullShow = null
        adsEvents = null
    }


}