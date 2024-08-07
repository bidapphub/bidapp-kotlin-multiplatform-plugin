
package io.bidapp.core

import cocoapods.bidapp.BIDAdInfo
import cocoapods.bidapp.BIDFullscreenLoadDelegateProtocol
import cocoapods.bidapp.BIDInterstitial
import cocoapods.bidapp.BIDInterstitialDelegateProtocol
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.darwin.NSObject


@OptIn(ExperimentalForeignApi::class)
public actual class BIDInterstitial actual constructor()  {
    private var interstitial : BIDInterstitial? = BIDInterstitial()
    private var interstitialShowDelegate : BIDInterstitialDelegateProtocol? = null
    private var interstitialLoadDelegate : BIDFullscreenLoadDelegateProtocol? = null


    public actual fun showInterstitial(
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

    public actual fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?) {
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

    public actual fun setAutoLoad(isAutoLoad: Boolean) {
    interstitial?.autoload = isAutoLoad
    }

    public actual fun isInterstitialReady(): Boolean {
        return interstitial?.isAdReady() ?: false
    }

    public actual fun load() {
    interstitial?.load()
    }

    public actual fun destroy() {
        interstitial = null
        interstitialLoadDelegate = null
        interstitialShowDelegate = null
    }

}

