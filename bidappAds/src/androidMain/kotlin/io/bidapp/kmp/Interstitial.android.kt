package io.bidapp.kmp

import android.app.Activity
import io.bidapp.kmp.BIDFullLoad
import io.bidapp.kmp.BIDFullShow
import io.bidapp.sdk.AdInfo
import io.bidapp.sdk.BIDFullscreenLoadDelegate
import io.bidapp.sdk.BIDInterstitialDelegate
import io.bidapp.sdk.Interstitial


public actual class BIDInterstitial actual constructor(){
    private var interstitial : Interstitial? = createInterstitial(BidappAds.getActivity())
    private var loadDelegate : BIDFullscreenLoadDelegate? = null
    private var showDelegate : BIDInterstitialDelegate? = null

    public actual fun showInterstitial(bidShowDelegate : BIDFullShow?) {
        if (BidappAds.getActivity() == null || interstitial == null) return
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
            BidappAds.getActivity()?.let { interstitial?.showWithDelegate(it, showDelegate) }
         }
        else BidappAds.getActivity()?.let { interstitial?.showWithDelegate(it, null) }
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
            interstitial?.setLoadDelegate(loadDelegate as BIDFullscreenLoadDelegate)
        }
    }

    public actual fun setAutoLoad(isAutoLoad: Boolean) {
        interstitial?.setAutoload(isAutoLoad)
    }

    public actual fun isInterstitialReady(): Boolean {
        return interstitial?.isAdReady() ?: false
    }

    public actual fun load() {
        interstitial?.load()
    }
    public actual fun destroy(){
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


