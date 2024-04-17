package io.bidapp.kotlin_multiplatform

import android.app.Activity
import io.bidapp.sdk.AdInfo
import io.bidapp.sdk.BIDFullscreenLoadDelegate
import io.bidapp.sdk.BIDInterstitialDelegate
import io.bidapp.sdk.Interstitial


actual class BIDInterstitial actual constructor(activity : Any?){
    private var interstitial : Interstitial? = createInterstitial(activity)
    private var loadDelegate : BIDFullscreenLoadDelegate? = null
    private var showDelegate : BIDInterstitialDelegate? = null

    actual fun showInterstitial(activity: Any?, bidShowDelegate : BIDFullShow?) {
        if ((activity as? Activity) == null || interstitial == null) return
        if (bidShowDelegate!= null) {
            showDelegate = object : BIDInterstitialDelegate{
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

            }
            interstitial?.showWithDelegate(activity, showDelegate)
         }
        else interstitial?.showWithDelegate(activity, null)
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
            interstitial?.setLoadDelegate(loadDelegate as BIDFullscreenLoadDelegate)
        }
    }

    actual fun setAutoLoad(isAutoLoad: Boolean) {
        interstitial?.setAutoload(isAutoLoad)
    }

    actual fun isInterstitialReady(): Boolean {
        return interstitial?.isAdReady() ?: false
    }

    actual fun load() {
        interstitial?.load()
    }
    actual fun destroy(){
        interstitial?.destroy()
        interstitial = null
        loadDelegate = null
        showDelegate = null
    }

    private fun createInterstitial(activity: Any?) : Interstitial?{
        return if ((activity as? Activity) == null) null
        else Interstitial(activity)
    }
}


