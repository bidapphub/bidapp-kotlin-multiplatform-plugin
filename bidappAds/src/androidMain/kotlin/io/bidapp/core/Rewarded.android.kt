package io.bidapp.core

import android.app.Activity
import io.bidapp.sdk.AdInfo
import io.bidapp.sdk.BIDFullscreenLoadDelegate
import io.bidapp.sdk.BIDRewardedDelegate
import io.bidapp.sdk.Rewarded



public actual class BIDRewarded actual constructor(){
    private var rewarded = createReward(BidappAds.getActivity())
    private var loadDelegate : BIDFullscreenLoadDelegate? = null
    private var showDelegate : BIDRewardedDelegate? = null

    public actual fun showRewarded(bidShowDelegate : BIDFullShow?) {
        if (BidappAds.getActivity() == null || rewarded == null) return
        if (bidShowDelegate != null) {
            showDelegate = object : BIDRewardedDelegate {
                override fun allNetworksDidFailToDisplayAd() {
                    bidShowDelegate.allNetworksFailedToDisplay("All Networks Did Fail To Display Ad")
                }

                override fun didClickAd(adInfo: AdInfo?) {
                    bidShowDelegate.click(createBidAdInfo(adInfo))
                }

                override fun didDisplayAd(adInfo: AdInfo?) {
                    bidShowDelegate.display(createBidAdInfo(adInfo))
                }

                override fun didFailToDisplayAd(adInfo: AdInfo?, error: Error) {
                    bidShowDelegate.failToDisplay(createBidAdInfo(adInfo), error.message.toString())
                }

                override fun didHideAd(adInfo: AdInfo?) {
                    bidShowDelegate.hide(createBidAdInfo(adInfo))
                }

                override fun didRewardUser() {
                    bidShowDelegate.reward()
                }

            }
            BidappAds.getActivity()?.let { rewarded?.showWithDelegate(it, showDelegate) }
        }
        else BidappAds.getActivity()?.let { rewarded?.showWithDelegate(it, null) }
    }

    public actual fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?) {
        if (bidLoadDelegate != null) {
            loadDelegate = object : BIDFullscreenLoadDelegate {
                override fun didFailToLoad(adInfo: AdInfo?, error: Error) {
                    bidLoadDelegate.failLoad(createBidAdInfo(adInfo), error.message.toString())
                }

                override fun didLoad(adInfo: AdInfo?) {
                    bidLoadDelegate.load(createBidAdInfo(adInfo))
                }

            }
            rewarded?.setLoadDelegate(loadDelegate as BIDFullscreenLoadDelegate)
        }
    }

    public actual fun setAutoLoad(isAutoLoad: Boolean) {
        rewarded?.setAutoload(isAutoLoad)
    }

    public actual fun isInterstitialReady(): Boolean {
        return rewarded?.isAdReady() ?: false
    }

    public actual fun load() {
        rewarded?.load()
    }
    public actual fun destroy(){
        rewarded?.destroy()
        rewarded = null
    }

    private fun createReward(activity: Any?) : Rewarded?{
        return if ((activity as? Activity) == null) null
        else Rewarded(activity)
    }
}

