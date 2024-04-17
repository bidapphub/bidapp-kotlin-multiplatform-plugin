package io.bidapp.kotlin_multiplatform

import android.app.Activity
import io.bidapp.sdk.AdInfo
import io.bidapp.sdk.BIDFullscreenLoadDelegate
import io.bidapp.sdk.BIDRewardedDelegate
import io.bidapp.sdk.Rewarded



actual class BIDRewarded actual constructor(activity : Any?){
    private var rewarded = createReward(activity)
    private var loadDelegate : BIDFullscreenLoadDelegate? = null
    private var showDelegate : BIDRewardedDelegate? = null

    actual fun showRewarded(applicationActivity: Any?, bidShowDelegate : BIDFullShow?) {
        if ((applicationActivity as? Activity) == null || rewarded == null) return
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
            rewarded?.showWithDelegate(applicationActivity, showDelegate)
        }
        else rewarded?.showWithDelegate(applicationActivity, null)
    }

    actual fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?) {
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

    actual fun setAutoLoad(isAutoLoad: Boolean) {
        rewarded?.setAutoload(isAutoLoad)
    }

    actual fun isInterstitialReady(): Boolean {
        return rewarded?.isAdReady() ?: false
    }

    actual fun load() {
        rewarded?.load()
    }
    actual fun destroy(){
        rewarded?.destroy()
        rewarded = null
    }

    private fun createReward(activity: Any?) : Rewarded?{
        return if ((activity as? Activity) == null) null
        else Rewarded(activity)
    }
}

