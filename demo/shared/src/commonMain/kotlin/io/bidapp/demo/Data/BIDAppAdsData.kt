package io.bidapp.demo.Data

import io.bidapp.demo.log
import io.bidapp.kmp.BIDAdFormat
import io.bidapp.kmp.BIDAdInfo
import io.bidapp.kmp.BIDBanner
import io.bidapp.kmp.BIDBannerShow
import io.bidapp.kmp.BIDFullLoad
import io.bidapp.kmp.BIDFullShow
import io.bidapp.kmp.BIDInterstitial
import io.bidapp.kmp.BIDRewarded
import io.bidapp.kmp.getPlatformName

class BIDAppAdsData {
    private var interstitial: io.bidapp.kmp.BIDInterstitial? = null
    private var rewarded: io.bidapp.kmp.BIDRewarded? = null
    private var banner: io.bidapp.kmp.BIDBanner? = null
    internal var adsEvents : AdsEvents? = null
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
    private var bannerShow : BIDBannerShow? = object : BIDBannerShow {
        override fun display(info: BIDAdInfo, bidBannerView: io.bidapp.kmp.BIDBanner) {
            adsEvents?.displayBanner()
            log("Display - adtag: ${info.adTag}")
        }

        override fun failToDisplay(info: BIDAdInfo, bidBannerView: io.bidapp.kmp.BIDBanner, error: String) {
            log("Fail to displat - adtag: ${info.adTag} Error:$error")
        }

        override fun click(info: BIDAdInfo, bidBannerView: io.bidapp.kmp.BIDBanner) {
            log("Click - adtag: ${info.adTag}")
        }

        override fun load(info: BIDAdInfo, bidBannerView: io.bidapp.kmp.BIDBanner) {
            adsEvents?.loadBanner(info.networkId)
            log("Load - adtag: ${info.adTag}")
        }

        override fun allNetworksFailedToDisplay(error: String) {
            log("All networks fail to display - error: $error")
        }

    }
    private var fullShow : BIDFullShow? = object : BIDFullShow {
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

    fun initialization(activity: Any?) {
        interstitial = io.bidapp.kmp.BIDInterstitial(activity)
        interstitial?.setLoadDelegate(fullInterstitialLoad)
        rewarded = io.bidapp.kmp.BIDRewarded(activity)
        rewarded?.setLoadDelegate(fullRewardedLoad)
        createBanner(activity)
    }

    internal fun createBanner (activity : Any?) {
        banner = io.bidapp.kmp.BIDBanner(activity, BIDAdFormat.banner_320x50)
        bannerShow?.let { banner?.setBannerViewDelegate(it) }
    }

    internal fun destroyBanner(){
        banner?.destroy()
        banner = null
    }

    fun getInterstitial() : io.bidapp.kmp.BIDInterstitial?{
        return interstitial
    }

    fun getRewarded() : io.bidapp.kmp.BIDRewarded?{
        return rewarded
    }

    fun getBanner() : io.bidapp.kmp.BIDBanner?{
        return banner
    }

    fun getFullShow(): BIDFullShow?{
        return fullShow
    }

    fun destroy(){
        interstitial?.destroy()
        rewarded?.destroy()
        banner?.destroy()
        interstitial = null
        rewarded = null
        banner = null
        fullInterstitialLoad = null
        fullRewardedLoad = null
        fullShow = null
        bannerShow = null
        adsEvents = null
    }
}
