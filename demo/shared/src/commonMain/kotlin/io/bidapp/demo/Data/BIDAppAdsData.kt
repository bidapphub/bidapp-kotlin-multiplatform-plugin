package io.bidapp.demo.Data

import io.bidapp.demo.log
import io.bidapp.kotlin_multiplatform.BIDAdFormat
import io.bidapp.kotlin_multiplatform.BIDAdInfo
import io.bidapp.kotlin_multiplatform.BIDBanner
import io.bidapp.kotlin_multiplatform.BIDBannerShow
import io.bidapp.kotlin_multiplatform.BIDFullLoad
import io.bidapp.kotlin_multiplatform.BIDFullShow
import io.bidapp.kotlin_multiplatform.BIDInterstitial
import io.bidapp.kotlin_multiplatform.BIDRewarded
import io.bidapp.kotlin_multiplatform.getPlatformName

class BIDAppAdsData {
    private var interstitial: BIDInterstitial? = null
    private var rewarded: BIDRewarded? = null
    private var banner: BIDBanner? = null
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
        override fun display(info: BIDAdInfo, bidBannerView: BIDBanner) {
            adsEvents?.displayBanner()
            log("Display - adtag: ${info.adTag}")
        }

        override fun failToDisplay(info: BIDAdInfo, bidBannerView: BIDBanner, error: String) {
            log("Fail to displat - adtag: ${info.adTag} Error:$error")
        }

        override fun click(info: BIDAdInfo, bidBannerView: BIDBanner) {
            log("Click - adtag: ${info.adTag}")
        }

        override fun load(info: BIDAdInfo, bidBannerView: BIDBanner) {
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
        interstitial = BIDInterstitial(activity)
        interstitial?.setLoadDelegate(fullInterstitialLoad)
        rewarded = BIDRewarded(activity)
        rewarded?.setLoadDelegate(fullRewardedLoad)
        createBanner(activity)
    }

    internal fun createBanner (activity : Any?) {
        banner = BIDBanner(activity, BIDAdFormat.banner_320x50)
        bannerShow?.let { banner?.setBannerViewDelegate(it) }
    }

    internal fun destroyBanner(){
        banner?.destroy()
        banner = null
    }

    fun getInterstitial() : BIDInterstitial?{
        return interstitial
    }

    fun getRewarded() : BIDRewarded?{
        return rewarded
    }

    fun getBanner() : BIDBanner?{
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
