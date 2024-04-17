
package io.bidapp.kotlin_multiplatform

import cocoapods.bidapp.BIDAdInfo
import cocoapods.bidapp.BIDFullscreenLoadDelegateProtocol
import cocoapods.bidapp.BIDInterstitial
import cocoapods.bidapp.BIDInterstitialDelegateProtocol
import io.bidapp.kotlin_multiplatform.createBidAdInfo
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.darwin.NSObject


@OptIn(ExperimentalForeignApi::class)
actual class BIDInterstitial actual constructor(activity : Any?)  {
    private var interstitial : BIDInterstitial? = BIDInterstitial()
    private var interstitialShowDelegate : BIDInterstitialDelegateProtocol? = null
    private var interstitialLoadDelegate : BIDFullscreenLoadDelegateProtocol? = null


    actual fun showInterstitial(
        activity: Any?,
        bidShowDelegate: BIDFullShow?
    ) {
        interstitialShowDelegate = object : BIDInterstitialDelegateProtocol, NSObject(){
            override fun allNetworksDidFailToDisplayAd() {
                bidShowDelegate?.allNetworksFailedToDisplay("All networks did fail to display ad")
            }

            override fun didClickAd(adInfo: BIDAdInfo) {
                bidShowDelegate?.click(createBidAdInfo(adInfo))
            }

            override fun didDisplayAd(adInfo: BIDAdInfo) {
                bidShowDelegate?.display(createBidAdInfo(adInfo))
            }

            override fun didFailToDisplayAd(adInfo: BIDAdInfo, error: NSError) {
                bidShowDelegate?.failToDisplay(createBidAdInfo(adInfo), error.localizedDescription)
            }

            override fun didHideAd(adInfo: BIDAdInfo) {
                bidShowDelegate?.hide(createBidAdInfo(adInfo))
            }

            override fun viewControllerForDisplayAd(): UIViewController {
                return UIApplication.sharedApplication.keyWindow?.rootViewController!!
            }
        }

        interstitial?.showWithDelegate(interstitialShowDelegate as BIDInterstitialDelegateProtocol)
    }

    actual fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?) {
        interstitialLoadDelegate = object : BIDFullscreenLoadDelegateProtocol, NSObject(){
            override fun didFailToLoadAd(adInfo: BIDAdInfo, error: NSError) {
                bidLoadDelegate?.failLoad(createBidAdInfo(adInfo), error.localizedDescription())
            }

            override fun didLoadAd(adInfo: BIDAdInfo) {
                bidLoadDelegate?.load(createBidAdInfo(adInfo))
            }
        }
        interstitial?.setLoadDelegate(interstitialLoadDelegate)
    }

    actual fun setAutoLoad(isAutoLoad: Boolean) {
    interstitial?.autoload = isAutoLoad
    }

    actual fun isInterstitialReady(): Boolean {
        return interstitial?.isAdReady() ?: false
    }

    actual fun load() {
    interstitial?.load()
    }

    actual fun destroy() {
        interstitial = null
        interstitialLoadDelegate = null
        interstitialShowDelegate = null
    }

}

